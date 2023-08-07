package africa.semicolon.promescuous.service;

import africa.semicolon.promescuous.dto.request.RegisterRequest;
import africa.semicolon.promescuous.dto.response.RegisterUserResponse;
import africa.semicolon.promescuous.models.User;
import africa.semicolon.promescuous.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class PromiscuousUserService implements UserServices{
    private final UserRepository userRepository;


    @Override
    public RegisterUserResponse register(RegisterRequest registerRequest) {
        String email = registerRequest.getEmail();
        String password = registerRequest.getPassword();

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        User savedUser = userRepository.save(user);

        String emailResponse = MockEmailService.sendEmail(savedUser.getEmail());
        log.info("email sending info -> {}", emailResponse);

        RegisterUserResponse response = new RegisterUserResponse();
        response.setMessage("Registration is successful check your email for verification message");

        return response;
    }
}
