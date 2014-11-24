package controllers.api

import play.api.libs.json._
import play.api.mvc.{Action, Controller}
import play.api.libs.functional.syntax._

object ScalaConsumeBody extends Controller {

  val badRequest = BadRequest(Json.obj("error" -> "bad request: use a JSON with the 'group' field"))

  def consumeJson = Action {
    request =>
      val result = for {
        json <- request.body.asJson
        name <- (json \ "group").asOpt[String]
      } yield Ok(name)
      result.getOrElse(badRequest)
  }

  case class UserGroup(id: Long, group: String)

  def consumeToCaseClass = Action {
    request =>

      val userGroupRead: Reads[UserGroup] = (
        (JsPath \ "id").read[Long] and
        (JsPath \ "group").read[String]
      )(UserGroup.apply _)

      val result = for {
        json <- request.body.asJson
        userGroup <- json.asOpt[UserGroup](userGroupRead)
      } yield Ok(userGroup.group)
      result.getOrElse(badRequest)

  }

}