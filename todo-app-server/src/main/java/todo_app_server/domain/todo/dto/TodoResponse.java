package todo_app_server.domain.todo.dto;

import lombok.Builder;
import lombok.Getter;
import todo_app_server.domain.todo.entity.Todo;

@Getter
@Builder
public class TodoResponse {
    private Long todoId;
    private String content;
    private Boolean isCompleted;


    public static TodoResponse from(Todo todo) {
        return TodoResponse.builder()
                .todoId(todo.getId())
                .content(todo.getContent())
                .isCompleted(todo.getIsCompleted())
                .build();
    }
}
