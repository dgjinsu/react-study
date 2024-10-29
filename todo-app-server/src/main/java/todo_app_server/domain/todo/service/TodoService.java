package todo_app_server.domain.todo.service;

import todo_app_server.domain.todo.dto.TodoListResponse;
import todo_app_server.domain.todo.dto.TodoSaveRequest;
import todo_app_server.domain.todo.dto.TodoUpdateRequest;

public interface TodoService {

    // 아이템 저장
    Long saveTodo(TodoSaveRequest request);

    // 아이템 list 조회
    TodoListResponse getTodoList();

    // 아이템 업데이트
    void updateTodoContent(Long todoId, TodoUpdateRequest request);

    // 완료된 아이템 제거
    void deleteCompletedTodo();

    // 하나의 아이템 제거
    void deleteTodoById(Long todoId);
}