package todo_app_server.domain.todo.dto;

import lombok.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TodoListResponse {
    private List<TodoResponse> todoListResponseList;
    private Integer totalTodoNum;
    private Integer completedTodoNum;
}
