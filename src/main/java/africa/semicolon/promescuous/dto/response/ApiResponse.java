package africa.semicolon.promescuous.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Builder
@Getter
public class ApiResponse <T>{
    private T data;
}
