package africa.semicolon.promiscuous.services;

import africa.semicolon.promiscuous.dto.request.RegisterRequest;
import africa.semicolon.promiscuous.dto.request.UpdateRequest;
import africa.semicolon.promiscuous.dto.response.ApiResponse;
import africa.semicolon.promiscuous.dto.response.GetUserResponse;
import africa.semicolon.promiscuous.dto.response.UpdateRequestResponse;
import africa.semicolon.promiscuous.execptions.PromiscuousBaseException;
import africa.semicolon.promiscuous.service.UserServices;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static africa.semicolon.promiscuous.utils.AppsUtil.BLANK_SPACE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Slf4j
@Sql(scripts = {"/db/insert.sql"})
public class UserServicesTest {
    @Autowired
    private UserServices userServices;



    @Test
   public void testRegister(){
        RegisterRequest registerUserRequest = new RegisterRequest();
        registerUserRequest.setEmail("rixode7827@v1zw.com");
        registerUserRequest.setPassword("ola");
        var response =userServices.register(registerUserRequest);

        assertNotNull(response);
        assertNotNull(response.getMessage());

    }

    @Test
    public void testActivateUserAccount(){
        ApiResponse<?> activateUserResponse = userServices.activateUserAccount("abc1234.ertyuytyghdk.teyu7");
        assertThat(activateUserResponse).isNotNull();
    }

    @Test
    public void getUserById(){
        GetUserResponse response = userServices.getUserById(100L);
        assertThat(response).isNotNull();
    }

    @Test
    public void getAllUsers(){
        List<GetUserResponse> users = userServices.getAllUsers(1,5);
        log.info("users ---> {}", users);
        assertThat(users).isNotNull();
        assertThat(users.size()).isEqualTo(5);
    }

//    @Test
//    public void testThatUserCanLogin(){
//        LoginRequest loginRequest = new LoginRequest();
//        loginRequest.setEmail("test@gmail.com");
//        loginRequest.setPassword("password");
//
//        LoginResponse loginResponse = userServices.login(loginRequest);
//        assertThat(loginResponse).isNotNull();
//        String accessToken = loginResponse.getAccessToken();
//        assertThat(accessToken).isNotNull();
//    }

//    @Test
//    public void testThatExceptionIsThrownWhenUserAuthenticateWithBadDetails(){
//        LoginRequest loginRequest = new LoginRequest();
//        loginRequest.setEmail("test@gmail.com");
//        loginRequest.setPassword("bad_password");
//
//        assertThatThrownBy(()->userServices.login(loginRequest))
//                .isInstanceOf(BadCredentialsException.class);
//
//    }


    @Test
    public void testThatUserCanUpdateAccount() throws JsonPatchException {
        UpdateRequest request = buildUpdateRequest();

       UpdateRequestResponse updateRequestResponse = userServices.UpdateUser(request, 100L);
       assertThat(updateRequestResponse).isNotNull();

       GetUserResponse userResponse = userServices.getUserById(100L);

       String fullName = userResponse.getFullName();
       String expectedFullName = new StringBuffer()
                                                    .append(request.getFirstName())
                                                    .append(BLANK_SPACE)
                                                    .append(request.getLastName())
                                                    .toString();

       assertThat(fullName).isEqualTo(expectedFullName);


    }

    private UpdateRequest buildUpdateRequest(){
       UpdateRequest request = new UpdateRequest();
        Set<String> interests = Set.of("Swimming", "Sport");
        request.setDateOfBirth(LocalDate.of(2003,2,24));
        request.setFirstName("Ola");
        request.setLastName("Ola");
        MultipartFile profileImage = getTestImage();
        request.setProfileImage(profileImage);
        request.setInterests(interests);
        request.setStreet("Lekki");
        request.setCountry("Ethiopia");
        request.setState("Lagos");
        request.setHouseNumber("16A");

        return request;
    }
    private MultipartFile getTestImage(){
        //obtain a path that point to the test image

        Path path = Paths.get("C:\\Users\\DELL\\Documents\\springProject\\promisciousApp\\promiscious\\src\\test\\resources\\images\\jerry.jpg");
        // create stream that can read from file that pointed to my path
        try (var inputStream = Files.newInputStream(path)){
            MultipartFile image = new MockMultipartFile("test_image", inputStream);
            return image;
        }catch (Exception exception){
            throw new PromiscuousBaseException(exception.getMessage());
        }
    }
}
