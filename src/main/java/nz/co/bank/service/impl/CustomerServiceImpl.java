package nz.co.bank.service.impl;

import java.util.Optional;

import nz.co.bank.dto.CustomerDTO;
import nz.co.bank.entity.Customer;
import nz.co.bank.repo.CustomerRepository;
import nz.co.bank.service.CustomerService;

public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Optional<CustomerDTO> getById(Long id) {
        return customerRepository.findById(id).map(customer -> new CustomerDTO(customer.getId(), customer.getName()));
    }

    @Override
    public CustomerDTO create(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setName(customerDTO.name());
        return Optional.of(customerRepository.save(customer)).map(custEntity -> new CustomerDTO(custEntity.getId(), custEntity.getName())).get();
    }

}
