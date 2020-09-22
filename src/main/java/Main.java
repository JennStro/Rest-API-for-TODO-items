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
        get("/hello", (req, res) -> "Hello World!");

        Todo todoItem = new Todo();
        todoItem.setId(UUID.randomUUID());
        todoItem.setDescription("Empty trash can");
        todoItem.setSummary("Summary");
        items.add(todoItem);

        String jsonTodo = new Gson().toJson(todoItem);

        get("/todo", (req, res) -> jsonTodo);

        post("/", (req, res) -> {
            Todo item = fromJson(req.body());
            item.setId(UUID.randomUUID());
            items.add(item);
            return new Gson().toJson(item);
        });

        put("/", (request, response) -> {
            Todo updatedItem = new Gson().fromJson(request.body(), Todo.class);
            System.out.println(request.body());
            System.out.println(items);
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).getId().equals(updatedItem.getId())) {
                    items.set(i, updatedItem);
                    return updatedItem.toJson();
                }
            }
            return "Could not update";
        });

        delete("/", (request, response) -> {
            return request.body();
        });


    }

}
