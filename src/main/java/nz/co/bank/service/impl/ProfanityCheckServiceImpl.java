package nz.co.bank.service.impl;

import java.util.Optional;

import nz.co.bank.service.ProfanityCheckService;

public class ProfanityCheckServiceImpl implements ProfanityCheckService{

    @Override
    public boolean isAcceptable(String text) {
        // TODO : Could Use a reliable 3rd party API to validate this.
        // Ensure most recent XXXX text is cached in memory.
        // Handle cases where the 3rd party API may not be available. 
        return true;
    }

}
