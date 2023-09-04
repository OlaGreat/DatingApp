package africa.semicolon.promiscuous.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class UploadMediaRequest {
    private MultipartFile media;
}
