package controllers.api;

import com.fasterxml.jackson.databind.JsonNode;
import models.java.UserGroup;
import play.libs.F;
import play.libs.Json;
import play.libs.ws.WS;
import play.libs.ws.WSResponse;
import play.mvc.Controller;
import play.mvc.Result;

public class JavaWithWS extends Controller {

    public static F.Promise<Result> consumeJson() {
        return WS.url("http://localhost:9000/api/java/manually").get().map(new F.Function<WSResponse, Result>() {
            @Override
            public Result apply(WSResponse wsResponse) throws Throwable {

                JsonNode json = wsResponse.asJson();
                // be careful of UnrecognizedPropertyException
                UserGroup userGroup = Json.fromJson(json, UserGroup.class);
                return ok(Json.toJson(userGroup));
            }
        });
    }

    public static F.Promise<Result> sendAndConsumeJson() {
        JsonNode json = request().body().asJson();
        return WS.url("http://localhost:9000/api/java/consumeToPOJO").post(json).map(new F.Function<WSResponse, Result>() {
            @Override
            public Result apply(WSResponse wsResponse) throws Throwable {
                JsonNode json = wsResponse.asJson();
                return ok(json);
            }
        });
    }
}
