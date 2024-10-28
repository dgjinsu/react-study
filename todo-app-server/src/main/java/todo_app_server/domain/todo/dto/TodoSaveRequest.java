package todo_app_server.domain.todo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class TodoSaveRequest {
    @Schema(description = "TODO 내용", example = "양치하기")
    private String content;
}
