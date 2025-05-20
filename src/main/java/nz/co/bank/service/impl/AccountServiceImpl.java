package nz.co.bank.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import nz.co.bank.dto.AccountDTO;
import nz.co.bank.dto.CustomerDTO;
import nz.co.bank.dto.request.CreateAccountDTO;
import nz.co.bank.entity.Account;
import nz.co.bank.repo.AccountRepository;
import nz.co.bank.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    @Cacheable(value = "accounts", key = "#customerId")
    public List<AccountDTO> getAllAccounts(CustomerDTO customerDTO) {
        Account account = new Account();
        account.setCustomerId(customerDTO.id());
        return accountRepository.findAll(Example.of(account)).stream().map(accEntity -> new AccountDTO(accEntity.getId(), accEntity.getAccountNumber(), account.getCustomerId(), accEntity.getNickName())).collect(Collectors.toList());
    }

    @Override
    @Cacheable(value = "account", key = "#id")
    public Optional<AccountDTO> getById(Long id) {
        return accountRepository.findById(id).map(account -> new AccountDTO(account.getId(), account.getAccountNumber(), account.getCustomerId(), account.getNickName()));
    }

    @Override
    @CacheEvict(value = "accounts", key = "#customerId")
    public Optional<AccountDTO> create(CustomerDTO customerDTO, CreateAccountDTO accountDTO) throws Exception {

        if(getAllAccounts(customerDTO).size() >= 5) {
            throw new Exception("User cannot have more than 5 accounts");
        }

        Account account = new Account();
        account.setNickName(accountDTO.nickName());
        account.setCustomerId(customerDTO.id());

        return Optional.of(accountRepository.save(account)).map(accEntity -> new AccountDTO(accEntity.getId(), accEntity.getAccountNumber(), account.getCustomerId(), accEntity.getNickName()));
    }

}
