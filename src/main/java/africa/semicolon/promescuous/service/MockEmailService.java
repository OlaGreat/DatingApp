package africa.semicolon.promescuous.service;

public class MockEmailService {

    public static String sendEmail(String email){
        if (email!= null){
            return "successful";
        }
        throw new RuntimeException("Invalid email");
    }
}
