package africa.semicolon.promiscuous.security.services;

import africa.semicolon.promiscuous.models.User;
import africa.semicolon.promiscuous.security.model.SecureUser;
import africa.semicolon.promiscuous.service.UserServices;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PromiscuousUserDetailsServices implements UserDetailsService {

    private final UserServices userServices;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        User user = userServices.getUserByEmail(userEmail);
        UserDetails userDetails = new SecureUser(user);
        return userDetails;
    }
}

