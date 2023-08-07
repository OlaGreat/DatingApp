package africa.semicolon.promescuous.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Value("${mail.api.key}")
    private String mailConfig;

    public String getMailConfig(){
        return this.mailConfig;
    }
}
