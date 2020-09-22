import com.google.gson.Gson;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {
        get("/hello", (req, res) -> "Hello World!");

        Todo todoItem = new Todo();
        todoItem.setDescription("Empty trash can");
        todoItem.setSummary("Summary");

        String jsonTodo = new Gson().toJson(todoItem);

        get("/todo", (req, res) -> jsonTodo);
    }

}
