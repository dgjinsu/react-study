package todo_app_server.domain.todo.dto;

import lombok.Builder;
import lombok.Getter;
import todo_app_server.domain.todo.entity.Todo;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class TodoListResponse {
    private List<TodoResponse> todoListResponseList;
    private Integer totalTodoNum;
    private Integer completedTodoNum;

    public static TodoListResponse from(List<Todo> todoList) {
        int totalTodoNum = todoList.size();
        int completedTodoNum = (int) todoList.stream()
                .filter(Todo::getIsCompleted)
                .count();

        List<TodoResponse> todoResponseList = todoList.stream()
                .map(TodoResponse::from)
                .collect(Collectors.toList());

        return TodoListResponse.builder()
                .todoListResponseList(todoResponseList)
                .totalTodoNum(totalTodoNum)
                .completedTodoNum(completedTodoNum)
                .build();
    }
}
