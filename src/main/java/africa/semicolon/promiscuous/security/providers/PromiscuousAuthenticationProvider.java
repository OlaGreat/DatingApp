package africa.semicolon.promiscuous.security.providers;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;

import static africa.semicolon.promiscuous.execptions.ExceptionMessage.INVALID_CREDENTIALS_EXCEPTION;

@Component
@AllArgsConstructor
public class PromiscuousAuthenticationProvider implements AuthenticationProvider {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    //.1 take the username from the request (contained in the authentication) and use
    // the userDetailsService to look for the user in the db

    //.2 if user from 1 is found, use the password encoder to compare the password from the request
    //to the userPassword from the db

    //.3 if the password matches, the request is authenticated

    //.4 else , request isn't authenticated
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getPrincipal().toString();
        UserDetails user = userDetailsService.loadUserByUsername(email);

        String password = authentication.getCredentials().toString();
        boolean isValidPassword = passwordEncoder.matches(password, user.getPassword());
        if(isValidPassword){
            Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
            Authentication authenticationResult = new UsernamePasswordAuthenticationToken(email, password, authorities);

            return authenticationResult;
        };
        throw new BadCredentialsException(INVALID_CREDENTIALS_EXCEPTION.getMessage());

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
