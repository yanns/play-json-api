package controllers.api;

import com.fasterxml.jackson.databind.node.ObjectNode;
import models.java.UserGroup;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.ArrayList;
import java.util.List;

public class JavaRestAPI extends Controller {

    public static Result fromString() {
        String json = "{" +
                "  \"group\": \"play user group\"" +
                "}";
        return ok(Json.parse(json));
    }

    public static Result manually() {
        ObjectNode result = Json.newObject();
        result.put("id", 456l);
        result.put("group", "play user group");
        return ok(result);
    }

    public static Result fromPOJO() {
        UserGroup userGroup = new UserGroup(456l, "play user group");
        return ok(Json.toJson(userGroup));
    }

    public static Result fromPOJOs() {
        List<UserGroup> userGroups = new ArrayList<>();
        userGroups.add(new UserGroup(456l, "play user group"));
        userGroups.add(new UserGroup(748l, "scala user group"));
        return ok(Json.toJson(userGroups));
    }
}

