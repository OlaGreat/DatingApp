package africa.semicolon.promescuous.service;

import africa.semicolon.promescuous.dto.request.LoginRequest;
import africa.semicolon.promescuous.dto.request.RegisterRequest;
import africa.semicolon.promescuous.dto.request.UpdateRequest;
import africa.semicolon.promescuous.dto.response.*;
import africa.semicolon.promescuous.models.User;
import com.github.fge.jsonpatch.JsonPatch;

import java.util.List;

public interface UserServices {
    RegisterUserResponse register(RegisterRequest registerRequest);
      ApiResponse<?> activateUserAccount(String token);


      GetUserResponse getUserById(long id);
      List<GetUserResponse> getAllUsers(int page, int pageSize);


    LoginResponse login(LoginRequest loginRequest);


    UpdateRequestResponse UpdateUser(UpdateRequest updateRequest, Long id);
    User findUserById(Long id);

}
