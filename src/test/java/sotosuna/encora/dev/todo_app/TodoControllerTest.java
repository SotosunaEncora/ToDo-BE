package sotosuna.encora.dev.todo_app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class TodoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TodoService todoService;

    @InjectMocks
    private TodoController todoController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(todoController).build();
    }

    @Test
    public void testGetAllTodos() throws Exception {
        List<Todo> todos = new ArrayList<>();
        Todo todo = new Todo();
        todo.setId(UUID.randomUUID());
        todo.setPriority("high");
        todo.setText("Task 1");
        todo.setDueDate(LocalDateTime.of(2024,8,8,0,0,0));
        todos.add(todo);


        when(todoService.getFilteredTodos(null, null, null, false, false)).thenReturn(todos);

        mockMvc.perform(get("/todos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(result -> {
                            System.out.println(result.getResponse().getContentAsString());
                })
                .andExpect(content().json("[{\"text\": \"Task 1\", \"priority\":\"high\",\"dueDate\":[2024,8,8,0,0],\"completed\":false}]"));

    }
}
