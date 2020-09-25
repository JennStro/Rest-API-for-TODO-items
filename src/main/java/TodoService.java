import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TodoService {

    private final TodoRepository repository = new TodoRepository();

    public List<Todo> getAllTodos() {
        return repository.getAllTodos();
    }

    public Todo save(Todo todo) {
        return repository.save(todo);
    }

    public Todo getTodoById(UUID id) {
        return repository.getTodoById(id);
    }

    public Todo update(Todo updatedTodo) {
        return repository.update(updatedTodo);
    }

    public Todo deleteTodoById(UUID id) {
        Todo todoToDelete = getTodoById(id);
        if (repository.delete(id)) {
            return todoToDelete;
        }
        return null;
    }
}
