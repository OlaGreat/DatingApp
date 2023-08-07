package africa.semicolon.promescuous.service;

import africa.semicolon.promescuous.dto.request.RegisterRequest;
import africa.semicolon.promescuous.dto.response.RegisterUserResponse;

public interface UserServices {
    RegisterUserResponse register(RegisterRequest registerRequest);
}
