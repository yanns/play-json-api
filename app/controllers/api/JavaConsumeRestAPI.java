package controllers.api;

import com.fasterxml.jackson.databind.JsonNode;
import models.java.UserGroup;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

public class JavaConsumeRestAPI extends Controller {

    @BodyParser.Of(BodyParser.Json.class)
    public static Result consumeJson() {
        JsonNode json = request().body().asJson();
        String name = json.findPath("group").textValue();
        if (name == null) {
            return badRequest("please set the group");
        }
        return ok(name);
    }

    @BodyParser.Of(BodyParser.Json.class)
    public static Result consumeToPOJO() {
        JsonNode json = request().body().asJson();
        // be careful of UnrecognizedPropertyException
        UserGroup userGroup = Json.fromJson(json, UserGroup.class);
        return ok(Json.toJson(userGroup));
    }
}

