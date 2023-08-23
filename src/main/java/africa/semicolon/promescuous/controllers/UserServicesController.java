package africa.semicolon.promescuous.controllers;

import africa.semicolon.promescuous.dto.request.RegisterRequest;
import africa.semicolon.promescuous.dto.request.UpdateRequest;
import africa.semicolon.promescuous.dto.response.GetUserResponse;
import africa.semicolon.promescuous.dto.response.RegisterUserResponse;
import africa.semicolon.promescuous.dto.response.UpdateRequestResponse;
import africa.semicolon.promescuous.service.UserServices;
import com.github.fge.jsonpatch.JsonPatch;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/user")
public class UserServicesController {
    UserServices userServices;

    @PostMapping("/signup")
    public ResponseEntity<RegisterUserResponse> registerUser(@RequestBody RegisterRequest registerRequest){
        var userResponse = userServices.register(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetUserResponse> getUserById (@PathVariable Long id){
        GetUserResponse user = userServices.getUserById(id);
        return ResponseEntity.ok().body(user);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateUserAccount(@RequestBody UpdateRequest updateRequest, @PathVariable Long id){
        UpdateRequestResponse response  = userServices.UpdateUser(updateRequest, id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateRequestResponse> updateUserProfile(@ModelAttribute UpdateRequest updateRequest, @PathVariable Long id){
      UpdateRequestResponse response =  userServices.UpdateUser(updateRequest, id);
      return ResponseEntity.ok(response);
    }

}
