package africa.semicolon.promescuous.service;

import africa.semicolon.promescuous.config.AppConfig;
import africa.semicolon.promescuous.dto.request.*;
import africa.semicolon.promescuous.dto.response.*;
import africa.semicolon.promescuous.execptions.BadCredentialsException;
import africa.semicolon.promescuous.execptions.ExceptionMessage;
import africa.semicolon.promescuous.execptions.PromiscuousBaseException;
import africa.semicolon.promescuous.execptions.UserNotFoundException;
import africa.semicolon.promescuous.models.Address;
import africa.semicolon.promescuous.models.Interest;
import africa.semicolon.promescuous.models.User;
import africa.semicolon.promescuous.repository.UserRepository;
import africa.semicolon.promescuous.service.cloud.CloudService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.TextNode;
import com.github.fge.jackson.jsonpointer.JsonPointer;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.JsonPatchOperation;
import com.github.fge.jsonpatch.ReplaceOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

import static africa.semicolon.promescuous.dto.response.ResponseMessage.*;
import static africa.semicolon.promescuous.execptions.ExceptionMessage.INVALID_CREDENTIALS_EXCEPTION;
import static africa.semicolon.promescuous.execptions.ExceptionMessage.USER_NOT_FOUND_EXCEPTION;
import static africa.semicolon.promescuous.utils.AppsUtil.*;
import static africa.semicolon.promescuous.utils.JwtUtils.*;

@Slf4j
@AllArgsConstructor
@Service
public class PromiscuousUserService implements UserServices{

    //Todo:
    private final UserRepository userRepository;
    private final MailServices mailServices;
    private final AppConfig appConfig;

    private final CloudService cloudService;


    @Override
    public RegisterUserResponse register(RegisterRequest registerRequest) {
        String email = registerRequest.getEmail();
        String password = registerRequest.getPassword();

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        User savedUser = userRepository.save(user);

        EmailNotificationRequest request = buildMailRequest(savedUser);
        mailServices.send(request);


        RegisterUserResponse registeredUserResponse = new RegisterUserResponse();
        registeredUserResponse.setMessage(USER_REGISTRATION_SUCCESSFUL.name());
        return registeredUserResponse;
    }

    @Override
    public ApiResponse<?> activateUserAccount(String token) {
        boolean isTestToken = token.equals(appConfig.getTestToken());
        if(isTestToken)return activateTestAccount();
        boolean isValidJwt = validateToken(token);
        if (isValidJwt) return activatedAccount(token);
        throw new PromiscuousBaseException(ExceptionMessage.ACCOUNT_ACTIVATION_FAILED_EXCEPTION.getMessage());
    }


    @Override
    public GetUserResponse getUserById(long id) {
        Optional<User> foundUser = userRepository.findById(id);
        User user = foundUser.orElseThrow(()-> new UserNotFoundException(USER_NOT_FOUND_EXCEPTION.getMessage()));
        GetUserResponse getUserResponse = buildGetUserResponse(user);
        return getUserResponse;
    }

    @Override
    public List<GetUserResponse> getAllUsers(int page, int pageSize) {
        Pageable pageable = buildPageRequest(page, pageSize);
        Page<User> usersPage = userRepository.findAll(pageable);

        List<User> foundUsers = usersPage.getContent();

        return foundUsers.stream()
                .map(PromiscuousUserService::buildGetUserResponse)
                .toList();
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        Optional<User> foundUser = userRepository.readByEmail(email);
        User user = foundUser.orElseThrow(()->new UserNotFoundException(
                String.format(USER_NOT_FOUND_EXCEPTION.getMessage(), email)
        ));
        boolean isValidPassword = matches(user.getPassword(), password);
        if(isValidPassword) return buildLoginResponse(email);
        throw new BadCredentialsException(INVALID_CREDENTIALS_EXCEPTION.getMessage());
    }

    @Override
    public UpdateRequestResponse UpdateUser(UpdateRequest updateRequest, Long id) {
        ModelMapper modelMapper = new ModelMapper();

      String url =  uploadImage(updateRequest.getProfileImage());


        User user = findUserById(id);



        Set<String> userInterest = updateRequest.getInterests();
        Set<Interest> interests = parseInterestForm(userInterest);
        user.setInterests(interests);

        Address userAddress = user.getAddress();
        if(userAddress == null) userAddress = new Address();
        modelMapper.map(updateRequest, userAddress);
        user.setAddress(userAddress);

        JsonPatch updatePatch = buildUpdatePatch(updateRequest);
        return applyPatch( updatePatch, user);
    }

    private String uploadImage(MultipartFile profileImage) {
        boolean isFormWithProfileImage = profileImage != null;
        if (isFormWithProfileImage) return cloudService.upload(profileImage);
        throw new RuntimeException("profile image  upload failed");
    }

    private static Set<Interest> parseInterestForm(Set<String> interests){
        return interests.stream()
                .map(interest -> Interest.valueOf(interest.toUpperCase()))
                .collect(Collectors.toSet());
    }

    private UpdateRequestResponse applyPatch( JsonPatch updatePatch,User user) {
        ObjectMapper objectMapper = new ObjectMapper();
        //convert user to JsonNode
        JsonNode userNode = objectMapper.convertValue(user, JsonNode.class);

        try {
            JsonNode updatedNode = updatePatch.apply(userNode);
            //convert updatedNode back to user
            user = objectMapper.convertValue(updatedNode, User.class);
            userRepository.save(user);

            return new UpdateRequestResponse(PROFILE_UPDATE_SUCCESSFUL.name());
        }catch (JsonPatchException exception) {
            throw new PromiscuousBaseException(exception.getMessage());
        }
    }

    private JsonPatch buildUpdatePatch(UpdateRequest updateRequest) {
        Field[] fields = updateRequest.getClass().getDeclaredFields();
       List<ReplaceOperation> operations = Arrays.stream(fields)
                                          .filter(field -> validateField(updateRequest, field)                                          )
                                          .map(field ->buildReplaceOperations(updateRequest, field))
                                          .toList();
       List<JsonPatchOperation> patchOperations = new ArrayList<>(operations);
       return new JsonPatch(patchOperations);
    }

    private boolean validateField(UpdateRequest updateRequest, Field field) {
        List<String> list = List.of("interests", "street", "houseNumber", "country", "state", "gender", "profileImage");
        field.setAccessible(true);
        try {
            return field.get(updateRequest)!=null && !list.contains(field.getName());
        } catch (IllegalAccessException exception) {
            throw new RuntimeException(exception);
        }

    }

    private static ReplaceOperation buildReplaceOperations(UpdateRequest updateRequest, Field field) {
        field.setAccessible(true);
        try {
            String path = JSON_PATCH_PATH_PREFIX + field.getName();
            //[{'op': replace, 'path' : "/value", "value" : 'name' }
            //{'op': replace, 'path' : "/firstName", "value" : 'Ola' }
            //
            // ]
            // jsonPointer is use to target the path
            // Textnode is use to target the value

            JsonPointer pointer = new  JsonPointer(path);

            String value = field.get(updateRequest).toString();
            TextNode node = new TextNode(value);
            ReplaceOperation operation = new ReplaceOperation(pointer, node);
            return operation;
         } catch (Exception exception) {
            throw new RuntimeException(exception);}
    }


    public User findUserById(Long id){
        Optional<User> foundUser = userRepository.findById(id);
        User user = foundUser.orElseThrow(()-> new UserNotFoundException(USER_NOT_FOUND_EXCEPTION.getMessage()));
        return user;
    }
    private static LoginResponse buildLoginResponse(String email) {
        String accessToken = generateToken(email);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setAccessToken(accessToken);
        return loginResponse;
    }

    private Pageable buildPageRequest(int page, int pageSize) {
        if (page<1 && pageSize<1) return PageRequest.of(0,10);
        if (page<1) return PageRequest.of(0,pageSize);
        if (pageSize<1) return PageRequest.of(page, pageSize);
        return PageRequest.of(page-1, pageSize);
    }

    private ApiResponse<Object> activatedAccount(String token) {
        String email = extractEmailFrom(token);
        User foundUser = userRepository.readByEmail(email).orElseThrow(()->new UserNotFoundException(
                String.format(ExceptionMessage.USER_WITH_EMAIL_NOT_FOUND_EXCEPTION.getMessage(), email)
        ));
        foundUser.setActive(true);
        User savedUser = userRepository.save(foundUser);
        GetUserResponse userResponse = buildGetUserResponse(savedUser);
        var activateUserResponse = buildActivationUserResponse(userResponse);
        return ApiResponse.builder().data(activateUserResponse).build();
    }

    private ActivateAccountResponse buildActivationUserResponse(GetUserResponse userResponse) {
        return ActivateAccountResponse.builder()
                .message(ACCOUNT_ACTIVATION_SUCCESSFUL.name())
                .user(userResponse)
                .build();
    }

    private static GetUserResponse buildGetUserResponse(User savedUser) {
        return GetUserResponse.builder()
                .id(savedUser.getId())
                .fullName(getFullName(savedUser))
                .phoneNumber(savedUser.getPhoneNumber())
                .email(savedUser.getEmail())
                .build();
    }

    private static String getFullName(User savedUser) {
        return savedUser.getFirstName() + BLANK_SPACE + savedUser.getLastName();
    }

    private static ApiResponse<?> activateTestAccount() {
        ApiResponse<?> activateAccountResponse =
               ApiResponse
                       .builder()
                       .build();
        return activateAccountResponse;
    }

    public EmailNotificationRequest buildMailRequest(User savedUser){
      EmailNotificationRequest request =  new EmailNotificationRequest();
        request.setRecipients(List.of(new Recipient(savedUser.getEmail())));
        request.setSubject(WELCOME_MAIL_SUBJECT);

        String activationLink = generateActivationLink(appConfig.getBaseUrl(),savedUser.getEmail());
        String emailTemplate = getMailTemplate();
        String mailContent = String.format(emailTemplate ,activationLink);

        request.setMailContent(mailContent);
        return request;

    }
}
