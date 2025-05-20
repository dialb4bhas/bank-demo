package nz.co.bank.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import nz.co.bank.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
