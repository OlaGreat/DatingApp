package africa.semicolon.promescuous.utils;

import africa.semicolon.promescuous.execptions.PromiscuousBaseException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.List;

import static africa.semicolon.promescuous.utils.JwtUtils.generateToken;


public class AppsUtil {

    public static final String APP_NAME = "promiscuous inc.";
    public static final String APP_EMAIL = "noreply@promiscuous.africads";
    public static final String WELCOME_MAIL_SUBJECT = "Welcome to promiscuous inc";

    public static final String NOTIFICATION_SENT = "Notification sent";

    public static final String BLANK_SPACE= " ";

    public static final String  EMPTY_STRING = "";

    private static final String MAIL_TEMPLATE_LOCATION = "C:\\Users\\DELL\\Documents\\springProject\\promisciousApp\\promiscious\\src\\main\\resources\\templates\\index (2).html";

    public static final String JSON_PATCH_PATH_PREFIX = "/";
    private static final String ACTIVATE_ACCOUNT_PATH = "/user/activate?code=";

    public static final String TEST_IMAGE_LOCATION = "C:\\Users\\DELL\\Documents\\springProject\\promisciousApp\\promiscious\\src\\test\\resources\\images\\jerry.jpg";


    public static String generateActivationLink(String baseUrl,String email){
        String token = generateToken(email);

        String activationLink = baseUrl + ACTIVATE_ACCOUNT_PATH + token;
        return activationLink;

    }


    public static String getMailTemplate(){
        Path templateLocation = Paths.get(MAIL_TEMPLATE_LOCATION);
        try {
            List<String> fileContents = Files.readAllLines(templateLocation);
            String template = String.join(EMPTY_STRING, fileContents);
            return template;

        }catch (IOException exception){
            throw new PromiscuousBaseException(exception.getMessage());
        }

    }

    public static boolean matches(String first, String second){
        return first.equals(second);
    }


}
