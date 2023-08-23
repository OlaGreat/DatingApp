package africa.semicolon.promescuous.service.cloud;

import africa.semicolon.promescuous.dto.response.ApiResponse;
import org.springframework.web.multipart.MultipartFile;

public interface CloudService {
    String upload(MultipartFile file);
}
