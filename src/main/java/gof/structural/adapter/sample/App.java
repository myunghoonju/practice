package gof.structural.adapter.sample;

import gof.structural.adapter.sample.adapter.AccountUserDetailsService;
import gof.structural.adapter.sample.client.LoginHandler;
import gof.structural.adapter.sample.client.target.UserDetailsService;

public class App {

    public static void main(String[] args) {
        AccountService accountService = new AccountService();
        UserDetailsService userDetailsService = new AccountUserDetailsService(accountService);
        LoginHandler loginHandler = new LoginHandler(userDetailsService);
        loginHandler.login("myunghoon", "myunghoon");
    }
}
