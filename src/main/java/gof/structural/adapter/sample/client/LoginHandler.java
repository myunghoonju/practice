package gof.structural.adapter.sample.client;

import gof.structural.adapter.sample.client.target.UserDetails;
import gof.structural.adapter.sample.client.target.UserDetailsService;

public class LoginHandler {

    UserDetailsService detailsService;

    public LoginHandler(UserDetailsService detailsService) {
        this.detailsService = detailsService;
    }

    public String login(String username, String password) {
        UserDetails userDetails = detailsService.loadUser(username);
        if (userDetails.getPassword().equals(password)) {
            return userDetails.getUsername();
        } else {
            throw new IllegalArgumentException();
        }
    }
}
