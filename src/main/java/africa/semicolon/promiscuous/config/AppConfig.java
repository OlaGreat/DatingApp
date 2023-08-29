package africa.semicolon.promiscuous.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Value("${mail.api.key}")
    private String mailConfig;
    @Value("${app.dev.token}")
    private String testToken;

    @Value("${app.base.url}")
    private String baseUrl;

    @Value("${cloud.api.name}")
    private String cloudApiName;

    @Value("${cloud.api.secret}")
    private String cloudApiSecret;

    @Value("${cloud.api.key}")
    private String cloudApiKey;



    public String getMailConfig(){
        return this.mailConfig;
    }

    public String getTestToken(){return this.testToken;}

    public String getBaseUrl(){
        return baseUrl;
    }

    public String getCloudApiName() {
        return cloudApiName;
    }

    public String getCloudApiSecret() {
        return cloudApiSecret;
    }

    public String getCloudApiKey() {
        return cloudApiKey;
    }
}
