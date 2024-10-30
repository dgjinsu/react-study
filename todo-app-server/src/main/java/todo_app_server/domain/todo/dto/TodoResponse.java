package todo_app_server.domain.todo.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TodoResponse {
    private Long todoId;
    private String content;
    private boolean isCompleted;
}
