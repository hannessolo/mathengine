import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        staticFileLocation("/public");

        get("/", (Request req, Response res) -> {
            HashMap<String, Object> model = new HashMap<>();
            return new VelocityTemplateEngine().render(
                    new ModelAndView(model, "templates/index/index.vm")
            );
        });

    }

}
