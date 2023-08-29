package africa.semicolon.promiscuous.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static africa.semicolon.promiscuous.utils.AppsUtil.APP_EMAIL;
import static africa.semicolon.promiscuous.utils.AppsUtil.APP_NAME;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailNotificationRequest {
    private final Sender sender = new Sender(APP_NAME, APP_EMAIL);

    @JsonProperty("to")
    private List<Recipient> recipients;

    @JsonProperty("cc")
    private List<String> copiedEmails;

    @JsonProperty("htmlContent")
    private String mailContent;
    private String textContent;
    private String subject;
}
