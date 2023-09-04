package africa.semicolon.promiscuous.service;

import africa.semicolon.promiscuous.dto.request.MediaReactionRequest;
import africa.semicolon.promiscuous.dto.response.UploadMediaResponse;
import africa.semicolon.promiscuous.models.Media;
import africa.semicolon.promiscuous.models.User;
import org.springframework.web.multipart.MultipartFile;

public interface MediaService {
    UploadMediaResponse uploadProfilePicture(MultipartFile file);
    UploadMediaResponse uploadMedia(MultipartFile file, User user);
    String reactToMedia(MediaReactionRequest mediaReactionRequest);

    Media getMediaByUser(User user);
}
