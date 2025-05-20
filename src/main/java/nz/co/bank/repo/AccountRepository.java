package nz.co.bank.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import nz.co.bank.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

}
