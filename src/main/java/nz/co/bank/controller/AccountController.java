package nz.co.bank.controller;

import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import nz.co.bank.dto.AccountDTO;
import nz.co.bank.dto.CustomerDTO;
import nz.co.bank.dto.request.CreateAccountDTO;
import nz.co.bank.service.AccountService;
import nz.co.bank.service.CustomerService;
import nz.co.bank.service.ProfanityCheckService;

import org.springframework.http.HttpHeaders;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;
    private final CustomerService customerService;
    private final ProfanityCheckService profanityCheckService;

    public AccountController(AccountService accountService, CustomerService customerService, ProfanityCheckService profanityCheckService){
        this.accountService = accountService;
        this.customerService = customerService;
        this.profanityCheckService = profanityCheckService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getAccountById(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader, @PathVariable Long id) {
        return accountService.getById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AccountDTO> create(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader, @Valid @RequestBody CreateAccountDTO accountDTO) throws Exception {

        Long customerId = resolveCustomerIdFromRequest(authorizationHeader);

        // Make sure the customer exists
        CustomerDTO customerDTO = customerService.getById(customerId).orElseThrow();

        if(profanityCheckService.isAcceptable(accountDTO.nickName())) {
            return accountService.create(customerDTO, accountDTO).map(ResponseEntity::ok).orElseThrow();
        }

        return ResponseEntity.badRequest().build();

    }

    private Long resolveCustomerIdFromRequest(String authorizationHeader) {
        return 0L;
    }


}
