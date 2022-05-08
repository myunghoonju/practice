package gof.structural.adapter.sample;

import gof.structural.adapter.sample.client.target.UserDetails;
import gof.structural.adapter.sample.client.target.UserDetailsService;

public class AccountService implements UserDetailsService {

    public Account findAccountByUsername(String username) {
        Account acc = new Account();
        acc.setName(username);
        acc.setPassword(username);

        return acc;
    }

    public void createAccount(Account acc) {

    }

    public void updateaAccount(Account acc) {

    }

    @Override
    public UserDetails loadUser(String username) {
        return findAccountByUsername(username);
    }
}
