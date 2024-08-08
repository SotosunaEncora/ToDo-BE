package sotosuna.encora.dev.todo_app;

import sotosuna.encora.dev.todo_app.Todo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class TodoRepository {
    private final List<Todo> todos = new ArrayList<>();

    public List<Todo> findAll() {
        return todos;
    }

    public Optional<Todo> findById(UUID id) {
        return todos.stream().filter(todo -> todo.getId().equals(id)).findFirst();
    }

    public void save(Todo todo) {
        todos.add(todo);
    }

    public void update(UUID id, Todo updatedTodo) {
        findById(id).ifPresent(todo -> {
            todo.setText(updatedTodo.getText());
            todo.setPriority(updatedTodo.getPriority());
            todo.setDueDate(updatedTodo.getDueDate());
        });
    }

    public void deleteById(UUID id) {
        todos.removeIf(todo -> todo.getId().equals(id));
    }
}
