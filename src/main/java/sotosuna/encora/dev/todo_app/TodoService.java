package sotosuna.encora.dev.todo_app;

import sotosuna.encora.dev.todo_app.Todo;
import sotosuna.encora.dev.todo_app.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    private int priorityOrder(String priority) {
        return switch (priority.toLowerCase()) {
            case "high" -> 1;
            case "medium" -> 2;
            case "low" -> 3;
            default -> 4;
        };
    }

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> getFilteredTodos(String text, String priority, String status, boolean ascendingPriority, boolean ascendingDueDate) {
        return todoRepository.findAll().stream()
                .filter(todo -> (text == null || text.isEmpty() || todo.getText().toLowerCase().contains(text.toLowerCase())))
                .filter(todo -> (priority == null || priority.isEmpty() || todo.getPriority().equalsIgnoreCase(priority)))
                .filter(todo -> (status == null || status.isEmpty() ||
                        (status.equalsIgnoreCase("done") && todo.isCompleted()) ||
                        (status.equalsIgnoreCase("not_done") && !todo.isCompleted())))
                .sorted(getComparator(ascendingPriority, ascendingDueDate))
                .collect(Collectors.toList());
    }

    private Comparator<Todo> getComparator(boolean ascendingPriority, boolean ascendingDueDate) {
        Comparator<Todo> priorityComparator = Comparator.comparing((Todo todo) -> priorityOrder(todo.getPriority()));
        Comparator<Todo> dueDateComparator = Comparator.comparing(Todo::getDueDate);

        if (ascendingPriority) {
            priorityComparator = priorityComparator.reversed();
        }

        if (ascendingDueDate) {
            dueDateComparator = dueDateComparator.reversed();
        }

        return priorityComparator.thenComparing(dueDateComparator);
    }

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Optional<Todo> getTodoById(UUID id) {
        return todoRepository.findById(id);
    }

    public void addTodo(Todo todo) {
        todo.setCreatedAt(LocalDateTime.now());
        todoRepository.save(todo);
    }

    public void updateTodo(UUID id, Todo updatedTodo) {
        todoRepository.update(id, updatedTodo);
    }

    public void markTodoAsDone(UUID id) {
        todoRepository.findById(id).ifPresent(todo -> {
            todo.setCompleted(true);
            todo.setCompletedAt(LocalDateTime.now());  // Set completedAt with time information
        });
    }

    public void markTodoAsNotDone(UUID id) {
        todoRepository.findById(id).ifPresent(todo -> {
            todo.setCompleted(false);
            todo.setCompletedAt(null);  // Reset completedAt
        });
    }

    public void deleteTodoById(UUID id) {
        todoRepository.deleteById(id);
    }
}