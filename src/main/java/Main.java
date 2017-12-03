import calculus.Calculus;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.velocity.VelocityTemplateEngine;

import static spark.Spark.*;

import java.util.HashMap;

public class Main {

  public static void main(String[] args) {

    int port;

    try {
      port = Integer.parseInt(System.getenv("PORT"));
    } catch (NumberFormatException e) {
      port = 4567;
    }

    setPort(port);

    staticFileLocation("/public");

    get("/", (Request req, Response res) -> {
      HashMap<String, Object> model = new HashMap<>();
      return new VelocityTemplateEngine().render(
          new ModelAndView(model, "templates/index/index.vm")
      );
    });

    post("/evaluate", "application/json", ((request, response) -> {

      System.out.println(request.body());

      JSONParser parser = new JSONParser();

      String res;

      try {

        JSONObject data = (JSONObject) parser.parse(request.body());

        String expr = (String) data.get("expression");
        String action = (String) data.get("action");
        String param = (String) data.get("param");

        switch (action){
          case "evaluate":
            try {
              res = Double.toString(Calculus.evaluate(expr, Double.parseDouble(param)));
            } catch (NumberFormatException e) {
              res = null;
            }
            break;
          case "differentiate":
            try {
              res = Calculus.differentiate(expr, param);
            } catch (NumberFormatException e) {
              res = null;
            }
            break;
          default:
            res = null;
        }

      } catch (Exception e) {
        res = e.getMessage();
      }

      System.out.println(res);

      JSONObject resJson = new JSONObject();

      resJson.put("expression", res);

      return resJson.toString();

    }));

  }

}
