package todo_app_server.domain.todo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import todo_app_server.domain.todo.dto.TodoCompleteUpdateRequest;
import todo_app_server.domain.todo.dto.TodoContentUpdateRequest;
import todo_app_server.domain.todo.dto.TodoSaveRequest;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Todo {

    @Id
    @GeneratedValue
    @Column(name = "todo_id")
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Boolean isCompleted;

    @Builder
    public Todo(String content, Boolean isCompleted) {
        this.content = content;
        this.isCompleted = isCompleted;
    }

    public static Todo createEntity(TodoSaveRequest request) {
        return Todo.builder()
                .content(request.getContent())
                .isCompleted(false)
                .build();
    }

    public void updateContent(TodoContentUpdateRequest request) {
        this.content = request.getContent();
    }

    public void updateCompleted(TodoCompleteUpdateRequest request) {
        this.isCompleted = request.getIsComplete();
    }
}