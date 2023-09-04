package africa.semicolon.promiscuous.service;

import africa.semicolon.promiscuous.dto.request.MediaReactionRequest;
import africa.semicolon.promiscuous.dto.response.UploadMediaResponse;
import africa.semicolon.promiscuous.execptions.PromiscuousBaseException;
import africa.semicolon.promiscuous.models.Media;
import africa.semicolon.promiscuous.models.MediaReaction;
import africa.semicolon.promiscuous.models.User;
import africa.semicolon.promiscuous.repository.MediaRepository;
import africa.semicolon.promiscuous.service.cloud.CloudService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import static africa.semicolon.promiscuous.dto.response.ResponseMessage.*;

@Service
@AllArgsConstructor
public class PromiscuousMediaService implements MediaService{

    private final CloudService cloudService;
    private final MediaRepository mediaRepository;
    @Override
    public UploadMediaResponse uploadProfilePicture(MultipartFile file) {
        cloudService.upload(file);
        UploadMediaResponse response = new UploadMediaResponse();
        response.setMessage("Profile picture updated");
        return response;
    }

    @Override
    public UploadMediaResponse uploadMedia(MultipartFile file, User user) {
        String url = cloudService.upload(file);
        Media media = new Media();
        media.setUrl(url);
        media.setUser(user);
        mediaRepository.save(media);
        UploadMediaResponse uploadMediaResponse = new UploadMediaResponse();
        uploadMediaResponse.setMessage(url);
        return uploadMediaResponse;
    }

    @Override
    public String reactToMedia(MediaReactionRequest mediaReactionRequest) {
        Media media=mediaRepository.findById(mediaReactionRequest.getMediaId())
                .orElseThrow(()->
                        new PromiscuousBaseException(MEDIA_NOT_FOUND.name()));
        MediaReaction reaction = new MediaReaction();
        reaction.setReaction(mediaReactionRequest.getReaction());
        reaction.setUser(mediaReactionRequest.getUserId());
        media.getReactions().add(reaction.getReaction());
        mediaRepository.save(media);
        return SUCCESS.name();
    }

    @Override
    public Media getMediaByUser(User user) {
        return mediaRepository.findByUser(user).orElseThrow(
                ()->new PromiscuousBaseException(RESOURCE_NOT_FOUND.name())
        );
    }
}
