package controllers.api

import play.api.Play.current
import play.api.libs.concurrent.Execution.Implicits._
import play.api.libs.json._
import play.api.libs.ws.WS
import play.api.mvc.{Action, Controller}

import scala.concurrent.Future

object ScalaWithWS extends Controller {

  val badRequest = BadRequest(Json.obj("error" -> "bad request: use a JSON"))

  def consumeJson = Action.async {
    WS.url("http://localhost:9000/api/scala/fromString").get().map {
      response =>
        Ok(response.json)
    }
  }

  def sendAndConsumeJson = Action.async {
    request =>
      request.body.asJson.fold(Future.successful(badRequest)) { json =>
        WS.url("http://localhost:9000/api/java/consumeToPOJO").post(json).map {
          response =>
            Ok(response.json)
        }
      }
  }

}