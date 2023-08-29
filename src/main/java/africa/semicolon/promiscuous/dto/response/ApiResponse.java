package africa.semicolon.promiscuous.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ApiResponse <T>{
    private T data;
}
