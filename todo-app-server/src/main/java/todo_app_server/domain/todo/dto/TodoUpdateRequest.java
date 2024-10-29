package todo_app_server.domain.todo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class TodoUpdateRequest {
    @Schema(description = "TODO 내용", example = "청소하기")
    private String content;
    @Schema(description = "TODO 완료 여부", example = "true")
    private Boolean isComplete;
}
