package controllers.api;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class JavaRestAPI extends Controller {

    public static Result fromString() {
        String json = "{" +
                "  \"field\": \"value\"" +
                "}";
        return ok(Json.parse(json));
    }
}
