package africa.semicolon.promescuous.utils;

import africa.semicolon.promescuous.config.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static africa.semicolon.promescuous.utils.AppsUtil.generateActivationLink;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class AppsUtilTest {
    @Autowired
    private AppConfig appConfig;

    @Test
    void testGenerateLink(){
        String activateLink = generateActivationLink(appConfig.getBaseUrl(),"test@gmail.com");
        log.info("activate link ---> {}", activateLink);
        assertThat(activateLink).contains("http://localhost:8080/activate");
    }


    @Test
    public void generateToken(){
        String email = "test@email.com";
        String token = JwtUtils.generateToken(email);
        log.info("generated token ----> {}", token);
        assertThat(token).isNotNull();

    }

}