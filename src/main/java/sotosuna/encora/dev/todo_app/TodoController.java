package sotosuna.encora.dev.todo_app;

import sotosuna.encora.dev.todo_app.Todo;
import sotosuna.encora.dev.todo_app.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/todos")
public class TodoController {
    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public List<Todo> getAllTodos(
        @RequestParam(required = false) String text,
        @RequestParam(required = false) String priority,
        @RequestParam(required = false) String status) {
        return todoService.getFilteredTodos(text, priority, status);
    }


    @PostMapping
    public void createTodo(@RequestBody Todo todo) {
        todoService.addTodo(todo);
    }

    @PutMapping("/{id}")
    public void updateTodo(@PathVariable UUID id, @RequestBody Todo updatedTodo) {
        todoService.updateTodo(id, updatedTodo);
    }

    @PutMapping("/{id}/done")
    public void markTodoAsDone(@PathVariable UUID id) {
        todoService.markTodoAsDone(id);
    }

    @PutMapping("/{id}/not-done")
    public void markTodoAsNotDone(@PathVariable UUID id) {
        todoService.markTodoAsNotDone(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTodoById(@PathVariable UUID id) {
        todoService.deleteTodoById(id);
    }
}

