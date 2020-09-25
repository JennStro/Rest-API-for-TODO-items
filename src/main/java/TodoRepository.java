import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TodoRepository {

    private final List<Todo> database = new ArrayList<>();

    public Todo save(Todo todo) {
        todo.setId(UUID.randomUUID());
        database.add(todo);
        return todo;
    };

    public List<Todo> getAllTodos() {
        return database;
    }

    public Todo getTodoById(UUID id) {
        for (Todo item : database) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }

    public Todo update(Todo updatedTodo) {
        for (int i = 0; i < database.size(); i++) {
            if (database.get(i).getId().equals(updatedTodo.getId())) {
                database.set(i, updatedTodo);
                return updatedTodo;
            }
        }
        return null;
    }

    public boolean delete(UUID id) {
        return database.removeIf(item -> item.getId().equals(id));
    }

}
