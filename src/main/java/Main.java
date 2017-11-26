import calculus.Calculus;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
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

        post("/evaluate","application/json", ((request, response) -> {

            System.out.print(request.body());

            JSONParser parser = new JSONParser();

            String res;

            try {

                JSONObject data = (JSONObject) parser.parse(request.body());

                String expr = (String) data.get("expression");

                res = Calculus.evaluate(expr);

            } catch (ParseException e) {
                res = e.getMessage();
            }

            return res;

        }));

    }

}
