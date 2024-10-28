package todo_app_server.domain.todo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class TodoCompleteUpdateRequest {
    @Schema(description = "TODO ID", example = "1")
    private Long todoId;
    @Schema(description = "TODO 완료 여부", example = "true")
    private Boolean isComplete;
}
