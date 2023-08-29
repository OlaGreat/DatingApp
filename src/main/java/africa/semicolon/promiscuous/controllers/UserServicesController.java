package africa.semicolon.promiscuous.controllers;

import africa.semicolon.promiscuous.dto.request.RegisterRequest;
import africa.semicolon.promiscuous.dto.request.UpdateRequest;
import africa.semicolon.promiscuous.dto.response.GetUserResponse;
import africa.semicolon.promiscuous.dto.response.RegisterUserResponse;
import africa.semicolon.promiscuous.dto.response.UpdateRequestResponse;
import africa.semicolon.promiscuous.execptions.UserNotFoundException;
import africa.semicolon.promiscuous.service.UserServices;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/user")
public class UserServicesController {
    private final UserServices userServices;

    @PostMapping("/signup")
    public ResponseEntity<RegisterUserResponse> registerUser(@RequestBody RegisterRequest registerRequest){
        var userResponse = userServices.register(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetUserResponse> getUserById (@PathVariable Long id)throws UserNotFoundException {
        GetUserResponse user = userServices.getUserById(id);
        return ResponseEntity.ok().body(user);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateUserAccount(@RequestBody UpdateRequest updateRequest, @PathVariable Long id) throws JsonPatchException {
        UpdateRequestResponse response  = userServices.UpdateUser(updateRequest, id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateRequestResponse> updateUserProfile(@ModelAttribute UpdateRequest updateRequest, @PathVariable Long id) throws JsonPatchException {
      UpdateRequestResponse response =  userServices.UpdateUser(updateRequest, id);
      return ResponseEntity.ok(response);
    }

}
