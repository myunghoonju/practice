package gof.structural.adapter.sample.adapter;

import gof.structural.adapter.sample.Account;
import gof.structural.adapter.sample.AccountService;
import gof.structural.adapter.sample.client.target.UserDetails;
import gof.structural.adapter.sample.client.target.UserDetailsService;

public class AccountUserDetailsService implements UserDetailsService {

    AccountService service;

    public AccountUserDetailsService(AccountService service) {
        this.service = service;
    }

    @Override
    public UserDetails loadUser(String username) {
        Account acc = service.findAccountByUsername(username);
        return new AccountUserDetails(acc);
    }
}
