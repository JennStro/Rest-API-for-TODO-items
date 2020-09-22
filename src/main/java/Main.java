import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static spark.Spark.*;

public class Main {

    private static List<Todo> items = new ArrayList<>();

    public static Todo fromJson(String json) {
        return new Gson().fromJson(json, Todo.class);
    }

    public static void main(String[] args) {

        Todo todoItem = new Todo();
        todoItem.setId(UUID.randomUUID());
        todoItem.setDescription("Empty trash can");
        todoItem.setSummary("Summary");
        items.add(todoItem);

        get("/allTodos", (req, res) ->  items);

        get("/:id", (request, response) -> {
            for (Todo item : items) {
                if (item.getId().equals(UUID.fromString(request.params(":id")))) {
                    return item.toJson();
                }
            }
            return "Could not find item.";
        });

        post("/", (request, response) -> {
            Todo item = fromJson(request.body());
            item.setId(UUID.randomUUID());
            items.add(item);
            return item.toJson();
        });

        put("/", (request, response) -> {
            Todo updatedItem = new Gson().fromJson(request.body(), Todo.class);
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).getId().equals(updatedItem.getId())) {
                    items.set(i, updatedItem);
                    return updatedItem.toJson();
                }
            }
            return "Could not update";
        });

        delete("/:id", (request, response) ->
           items.removeIf(item -> item.getId().equals(UUID.fromString(request.params(":id"))))
        );


    }

}
