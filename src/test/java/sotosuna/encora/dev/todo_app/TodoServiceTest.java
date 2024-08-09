package sotosuna.encora.dev.todo_app;

import sotosuna.encora.dev.todo_app.Todo;
import sotosuna.encora.dev.todo_app.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoService todoService;

    private List<Todo> todos;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        todos = new ArrayList<>();
        Todo todo1 = new Todo();
        todo1.setId(UUID.randomUUID());
        todo1.setText("Task 1");
        todo1.setPriority("high");
        todo1.setDueDate(LocalDateTime.of(2024, 8, 8,0,0,0));

        Todo todo2 = new Todo();
        todo2.setId(UUID.randomUUID());
        todo2.setText("Task 2");
        todo2.setPriority("medium");
        todo2.setDueDate(LocalDateTime.of(2024, 8, 9,0,0,0));

        todos.add(todo1);
        todos.add(todo2);

        when(todoRepository.findAll()).thenReturn(todos);
    }

    @Test
    public void testGetFilteredTodosSortedAscending() {
        List<Todo> result = todoService.getFilteredTodos(null, null, null, false, false);
        assertEquals(2, result.size());
        assertEquals("Task 1", result.get(0).getText());
        assertEquals("Task 2", result.get(1).getText());
    }

    @Test
    public void testGetFilteredTodosSortedDescending() {
        List<Todo> result = todoService.getFilteredTodos(null, null, null, true, true);
        assertEquals(2, result.size());
        assertEquals("Task 2", result.get(0).getText());
        assertEquals("Task 1", result.get(1).getText());
    }

    @Test
    public void testGetFilteredTodosWithPriorityFilter() {
        List<Todo> result = todoService.getFilteredTodos(null, "high", null, true, true);
        assertEquals(1, result.size());
        assertEquals("Task 1", result.get(0).getText());
    }
}
