package africa.semicolon.promiscuous.service;

import africa.semicolon.promiscuous.dto.request.RegisterRequest;
import africa.semicolon.promiscuous.dto.request.UpdateRequest;
import africa.semicolon.promiscuous.dto.response.*;
import africa.semicolon.promiscuous.models.User;
import com.github.fge.jsonpatch.JsonPatchException;

import java.util.List;

public interface UserServices {
    RegisterUserResponse register(RegisterRequest registerRequest);
      ApiResponse<?> activateUserAccount(String token);


      GetUserResponse getUserById(long id);
      List<GetUserResponse> getAllUsers(int page, int pageSize);


//    LoginResponse login(LoginRequest loginRequest);


    UpdateRequestResponse UpdateUser(UpdateRequest updateRequest, Long id) throws JsonPatchException;
    User findUserById(Long id);

    User getUserByEmail(String userEmail);

}
