package controllers.api

import play.api.Play.current
import play.api.libs.concurrent.Execution.Implicits._
import play.api.libs.functional.syntax._
import play.api.libs.json.Reads._
import play.api.libs.json._
import play.api.libs.ws.WS
import play.api.mvc.{Action, Controller}

import scala.concurrent.Future

/**
 * demo json transformers: https://www.playframework.com/documentation/2.3.x/ScalaJsonTransformers
 */
object ScalaTransform extends Controller {

  val badRequest = BadRequest(Json.obj("error" -> "bad request: use a JSON"))

  val jsonTransformer: Reads[JsObject] = (
      (__ \ "id").json.prune and
      (__ \ "group").json.pickBranch
    ).reduce

  def transformJson = Action.async {
    request =>
      request.body.asJson.fold(Future.successful(badRequest)) { json =>
        WS.url("http://localhost:9000/api/java/consumeToPOJO").post(json).map {
          response =>
            response.json.transform(jsonTransformer) match {
              case JsError(errors) => BadRequest(JsError.toFlatJson(errors))
              case JsSuccess(transformed, _) => Ok(transformed)
            }
        }
      }
  }

}