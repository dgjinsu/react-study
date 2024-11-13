package todo_app_server.domain.todo.dto;

import lombok.*;

@Builder
public record TodoResponse(
        Long todoId,
        String content,
        Boolean isCompleted
) {
}
