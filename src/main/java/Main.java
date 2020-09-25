import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static spark.Spark.*;

public class Main {

    private static List<Todo> items = new ArrayList<>();

    private static TodoRepository repository = new TodoRepository();
    private static TodoService service = new TodoService();


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
            if (!(service.getTodoById(id) == null)) {
                return service.getTodoById(id).toJson();
            }
            return "Could not find item.";
        });

        post("/", (request, response) -> service.save(fromJson(request.body())).toJson());

        put("/", (request, response) -> {
            Todo updatedItem = fromJson(request.body());
            if (service.update(updatedItem) != null) {
                return service.update(updatedItem);
            }
            return "Could not update";
        });

        delete("/:id", (request, response) -> {
            UUID id = UUID.fromString(request.params(":id"));
            Optional maybeTodo = service.deleteTodoById(id);
            if (service.deleteTodoById(id) != null) {

            }
                };
        );


    }

}
