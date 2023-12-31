package africa.semicolon.promiscuous.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

import java.time.Instant;
import java.util.List;

import static africa.semicolon.promiscuous.utils.AppsUtil.APP_NAME;

public class JwtUtils {
    public static String generateVerificationToken(String email){
        //generate token with the user's email embedded in it
        //TODO: refactor
        String token = JWT.create()
                .withClaim("user",email)
                .withIssuer(APP_NAME)
                .withExpiresAt(Instant.now().plusSeconds(3600))
                .sign(Algorithm.HMAC512("secret"));
        return token;
    }

    public static String generateAccessToken(List<String > authorities){
        //generate token with the user's email embedded in it
        //TODO: refactor
        String token = JWT.create()
                .withClaim("roles", authorities)
                .withIssuer(APP_NAME)
                .withExpiresAt(Instant.now().plusSeconds(3600))
                .sign(Algorithm.HMAC512("secret"));
        return token;
    }

    public static boolean validateToken(String token){
        JWTVerifier verifier =  JWT.require(Algorithm.HMAC512("secret"))
                .withIssuer(APP_NAME)
                .withClaimPresence("user")
                .build();

        return verifier.verify(token)!=null;
    }

    public static String extractEmailFrom(String token){
        var claim = JWT.decode(token).getClaim("user");
        return (String) claim.asMap().get("user");
    }
}
