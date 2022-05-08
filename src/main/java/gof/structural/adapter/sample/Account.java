package gof.structural.adapter.sample;

import gof.structural.adapter.sample.client.target.UserDetails;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account implements UserDetails {

    private String name;
    private String password;
    private String email;

    @Override
    public String getUsername() {
        return this.name;
    }

    @Override
    public String getPassword() {
        return this.password;
    }
}
