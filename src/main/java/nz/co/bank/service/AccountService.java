package nz.co.bank.service;

import java.util.List;
import java.util.Optional;

import nz.co.bank.dto.AccountDTO;
import nz.co.bank.dto.CustomerDTO;
import nz.co.bank.dto.request.CreateAccountDTO;

public interface AccountService {

    List<AccountDTO> getAllAccounts(CustomerDTO customerDTO);
    Optional<AccountDTO> getById(Long id);
    Optional<AccountDTO> create(CustomerDTO customerDTO, CreateAccountDTO accountDTO) throws Exception;
    //TODO: Implement other account related services as needed.

}
