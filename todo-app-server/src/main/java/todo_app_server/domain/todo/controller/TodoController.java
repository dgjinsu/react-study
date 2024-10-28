package todo_app_server.domain.todo.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import todo_app_server.domain.todo.dto.*;
import todo_app_server.domain.todo.service.TodoService;
import todo_app_server.global.common.Response;

import java.util.List;

@RestController
@RequestMapping("/api/v1/todo")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @Operation(summary = "TODO 저장")
    @PostMapping("/")
    public ResponseEntity<Response<Long>> saveTodo(@RequestBody TodoSaveRequest request) {
        return ResponseEntity.ok(new Response<Long>(todoService.saveTodo(request), "TODO 저장 완료"));
    }

    @Operation(summary = "TODO 리스트 조회")
    @GetMapping("/list")
    public ResponseEntity<Response<TodoListResponse>> getTodoList() {
        return ResponseEntity.ok(new Response<TodoListResponse>(todoService.getTodoList(), "TODO 리스트 조회 완료"));
    }

    @Operation(summary = "TODO 내용 수정")
    @PutMapping("/content")
    public ResponseEntity<Response<Void>> updateTodoContent(@RequestBody TodoContentUpdateRequest request) {
        todoService.updateTodoContent(request);
        return ResponseEntity.ok(new Response<Void>("TODO 내용 수정 완료"));
    }

    @Operation(summary = "TODO 완료 여부 수정")
    @PutMapping("/completed")
    public ResponseEntity<Response<Void>> updateTodoContent(@RequestBody TodoCompleteUpdateRequest request) {
        todoService.updateTodoCompleted(request);
        return ResponseEntity.ok(new Response<Void>("TODO 완료 여부 수정 완료"));
    }

    @Operation(summary = "완료된 TODO 제거")
    @DeleteMapping("/completed")
    public ResponseEntity<Response<Void>> deleteCompletedTodo() {
        todoService.deleteCompletedTodo();
        return ResponseEntity.ok(new Response<Void>("완료된 TODO 제거 완료"));
    }
}
