package todo_app_server.domain.todo.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import todo_app_server.domain.todo.dto.*;
import todo_app_server.domain.todo.service.TodoService;
import todo_app_server.global.common.Response;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @Operation(summary = "TODO 저장")
    @PostMapping("/todo")
    public ResponseEntity<Response<Long>> saveTodo(@RequestBody TodoSaveRequest request) {
        return ResponseEntity.ok(new Response<Long>(todoService.saveTodo(request), "TODO 저장 완료"));
    }

    @Operation(summary = "TODO 리스트 조회")
    @GetMapping("/todos")
    public ResponseEntity<Response<TodoListResponse>> getTodoList() {
        return ResponseEntity.ok(new Response<TodoListResponse>(todoService.getTodoList(), "TODO 리스트 조회 완료"));
    }

    @Operation(summary = "TODO 수정")
    @PutMapping("/todo/{todoId}")
    public ResponseEntity<Response<Void>> updateTodoContent(@PathVariable Long todoId, @RequestBody TodoUpdateRequest request) {
        todoService.updateTodoContent(todoId, request);
        return ResponseEntity.ok(new Response<Void>("TODO 내용 수정 완료"));
    }

    @Operation(summary = "완료된 TODO 제거")
    @DeleteMapping("/todo/completed")
    public ResponseEntity<Response<Void>> deleteCompletedTodo() {
        todoService.deleteCompletedTodo();
        return ResponseEntity.ok(new Response<Void>("완료된 TODO 제거 완료"));
    }

    @Operation(summary = "TODO 개별 제거")
    @DeleteMapping("/todo/{todoId}")
    public ResponseEntity<Response<Void>> deleteTodoById(@PathVariable Long todoId) {
        todoService.deleteTodoById(todoId);
        return ResponseEntity.ok(new Response<Void>("완료된 TODO 제거 완료"));
    }
}
