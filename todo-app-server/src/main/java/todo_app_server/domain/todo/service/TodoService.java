package todo_app_server.domain.todo.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import todo_app_server.domain.todo.dto.*;
import todo_app_server.domain.todo.entity.Todo;
import todo_app_server.domain.todo.repository.TodoRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class TodoService {
    private final TodoRepository todoRepository;

    /**
     * todo 저장
     */
    public Long saveTodo(TodoSaveRequest request) {
        Todo todo = Todo.createEntity(request);
        return todoRepository.save(todo).getId();
    }

    /**
     * todo list 조회
     */
    @Transactional(readOnly = true)
    public TodoListResponse getTodoList() {
        List<Todo> todoList = todoRepository.findAll();
        return TodoListResponse.from(todoList);
    }

    /**
     * todo content 업데이트
     * @param request
     */
    public void updateTodoContent(TodoContentUpdateRequest request) {
        Todo todo = todoRepository.findById(request.getTodoId())
                .orElseThrow(() -> new EntityNotFoundException("정보를 찾을 수 없습니다."));

        todo.updateContent(request);
    }

    /**
     * todo 완료 여부 업데이트
     */
    public void updateTodoCompleted(TodoCompleteUpdateRequest request) {
        Todo todo = todoRepository.findById(request.getTodoId())
                .orElseThrow(() -> new EntityNotFoundException("정보를 찾을 수 없습니다."));

        todo.updateCompleted(request);
    }

    /**
     * 완료된 todo 제거
     */
    public void deleteCompletedTodo() {
        int deletedTodoCnt = todoRepository.deleteCompletedTodo();
        log.info("제거된 TODO 수: {}", deletedTodoCnt);
    }
}
