import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static spark.Spark.*;

public class Main {

    private static List<Todo> items = new ArrayList<>();

    private static TodoRepository repository = new TodoRepository();
    private static TodoService service = new TodoService(repository);


    public static Todo fromJson(String json) {
        return new Gson().fromJson(json, Todo.class);
    }

    public static void main(String[] args) {

        Todo todoItem = new Todo();
        todoItem.setId(UUID.randomUUID());
        todoItem.setDescription("Empty trash can");
        todoItem.setSummary("Summary");
        items.add(todoItem);

        get("/allTodos", (req, res) ->  service.getAllTodos());

        get("/:id", (request, response) -> {
            UUID id = UUID.fromString(request.params(":id"));
            Todo todo = service.getTodoById(id);
            if (todo != null) {
                return todo.toJson();
            }
            return "Could not find item.";
        });

        post("/", (request, response) -> service.save(fromJson(request.body())).toJson());

        put("/", (request, response) -> {
            Todo newItem = fromJson(request.body());
            Todo updatedItem = service.update(newItem);
            if (updatedItem != null) {
                return updatedItem.toJson();
            }
            return "Could not update";
        });

        delete("/:id", (request, response) -> {
            UUID id = UUID.fromString(request.params(":id"));
            Todo maybeTodo = service.deleteTodoById(id);
            if (maybeTodo != null) {
                return maybeTodo.toJson();
            }
            return "Could not delete";
        }
        );


    }

}
