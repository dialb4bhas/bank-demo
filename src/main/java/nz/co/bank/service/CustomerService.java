package nz.co.bank.service;

import java.util.Optional;

import nz.co.bank.dto.CustomerDTO;

public interface CustomerService {
    Optional<CustomerDTO> getById(Long id);
    CustomerDTO create(CustomerDTO customerDTO);
    //TODO: Implement other account related services as needed.
}
