package gof.structural.adapter.sample.adapter;

import gof.structural.adapter.sample.Account;
import gof.structural.adapter.sample.client.target.UserDetails;

public class AccountUserDetails implements UserDetails {

    private Account account;

    public AccountUserDetails(Account account) {
        this.account = account;
    }

    @Override
    public String getPassword() {
        return this.account.getPassword();
    }

    @Override
    public String getUsername() {
        return this.account.getName();
    }
}
