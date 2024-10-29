package todo_app_server.domain.todo.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import todo_app_server.domain.todo.dto.*;
import todo_app_server.domain.todo.entity.Todo;
import todo_app_server.domain.todo.repository.TodoRepository;
import todo_app_server.global.exception.ErrorCode;
import todo_app_server.global.exception.TodoAppException;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class TodoServiceImpl implements TodoService {
    private final TodoRepository todoRepository;

    /**
     * 아이템 저장
     */
    @Override
    public Long saveTodo(TodoSaveRequest request) {
        Todo todo = Todo.createEntity(request);
        return todoRepository.save(todo).getId();
    }

    /**
     * 아이템 list 조회
     */
    @Override
    @Transactional(readOnly = true)
    public TodoListResponse getTodoList() {
        List<Todo> todoList = todoRepository.findAll();
        return TodoListResponse.from(todoList);
    }

    /**
     * 아이템 업데이트
     */
    @Override
    public void updateTodoContent(Long todoId, TodoUpdateRequest request) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new TodoAppException(ErrorCode.TODO_NOT_FOUND));

        todo.updateTodo(request);
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