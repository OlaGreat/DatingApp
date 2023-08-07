package africa.semicolon.promescuous.services;

import africa.semicolon.promescuous.dto.request.RegisterRequest;
import africa.semicolon.promescuous.dto.response.RegisterUserResponse;
import africa.semicolon.promescuous.service.UserServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServicesTest {
    @Autowired
    private UserServices userServices;

    @Test
   public void testRegister(){
        RegisterRequest registerUserRequest = new RegisterRequest();
        registerUserRequest.setEmail("ola@gmail");
        registerUserRequest.setPassword("ola");
        RegisterUserResponse response =userServices.register(registerUserRequest);
        assertNotNull(response);
        assertNotNull(response.getMessage());




    }
}
