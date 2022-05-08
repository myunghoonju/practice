package gof.structural.adapter.sample.client.target;

public interface UserDetailsService {

   UserDetails loadUser(String username);
}
