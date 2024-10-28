package todo_app_server.domain.todo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class TodoContentUpdateRequest {
    @Schema(description = "TODO ID", example = "1")
    private Long todoId;
    @Schema(description = "TODO 내용", example = "청소하기")
    private String content;
}
