package todo_app_server.domain.todo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import todo_app_server.domain.todo.dto.*;
import todo_app_server.domain.todo.entity.Todo;
import todo_app_server.domain.todo.repository.TodoRepository;
import todo_app_server.global.exception.ErrorCode;
import todo_app_server.global.exception.TodoAppException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class TodoServiceImpl implements TodoService {
    private final TodoRepository todoRepository;
    private final TodoMapper todoMapper;

    /**
     * 아이템 저장
     */
    @Override
    public TodoResponse saveTodo(TodoSaveRequest request) {
        Todo todo = todoMapper.toEntity(request);
        Todo savedTodo = todoRepository.save(todo);
        return todoMapper.toResponse(savedTodo);
    }

    /**
     * 아이템 list 조회
     */
    @Override
    @Transactional(readOnly = true)
    public TodoListResponse getTodoList() {
        LocalDateTime now = LocalDateTime.now();
        log.info("현재 시각 :{}", now);
        List<Todo> todoList = todoRepository.findAll();
        return todoMapper.toResponseList(todoList);
    }

    /**
     * 아이템 업데이트
     */
    @Override
    public TodoResponse updateTodo(Long todoId, TodoUpdateRequest request) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new TodoAppException(ErrorCode.TODO_NOT_FOUND));

        todo.updateTodo(request);

        return todoMapper.toResponse(todo);
    }

    /**
     * 완료된 아이템 제거
     */
    @Override
    public void deleteCompletedTodo() {
        int deletedTodoCnt = todoRepository.deleteCompletedTodo();
        log.info("제거된 TODO 수: {}", deletedTodoCnt);
    }

    /**
     * 하나의 아이템 제거
     */
    @Override
    public void deleteTodoById(Long todoId) {
        todoRepository.deleteById(todoId);
    }
}