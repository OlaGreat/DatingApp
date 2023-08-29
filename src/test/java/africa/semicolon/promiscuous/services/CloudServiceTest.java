package africa.semicolon.promiscuous.services;

import africa.semicolon.promiscuous.service.cloud.CloudService;
import africa.semicolon.promiscuous.utils.AppsUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CloudServiceTest {
    @Autowired
    private  CloudService cloudService;


    @Test
    public void testUploadFile(){
        Path path = Path.of(AppsUtil.TEST_IMAGE_LOCATION);
        try(InputStream inputStream = Files.newInputStream(path)){
            MultipartFile file = new MockMultipartFile("testImage", inputStream);
           String cloudResponse = cloudService.upload(file);
           assertNotNull(cloudResponse);
           assertThat(cloudResponse).isNotNull();
        }catch (IOException exception){
         throw new RuntimeException(":(");
        }
    }
}
