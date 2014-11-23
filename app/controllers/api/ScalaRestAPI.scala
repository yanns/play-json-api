package controllers.api

import play.api.libs.json.{JsPath, JsValue, Writes, Json}
import play.api.libs.functional.syntax._
import play.api.mvc.{Action, Controller}

object ScalaRestAPI extends Controller {

  def fromString = Action {
    val json =
      """{
        |   "group": "play user group"
        |}""".stripMargin
    Ok(Json.parse(json))
  }

  def manually = Action {
    val result = Json.obj(
      "id" -> 456l,
      "group" -> "play user group"
    )
    Ok(result)
  }


  case class UserGroup(id: Long, group: String)

  def fromCaseClass1 = Action {

    val userGroup = UserGroup(456l, "play user group")

    // define how to serialize a UserGroup into json
    val userGroupWrite = new Writes[UserGroup] {
      override def writes(u: UserGroup): JsValue =
        Json.obj(
          "id" -> 456l,
          "group" -> "play user group"
        )
    }

    Ok(Json.toJson(userGroup)(userGroupWrite))
  }


  def fromCaseClass2 = Action {
    val userGroup = UserGroup(456l, "play user group")

    // define how to serialize a UserGroup into json
    val userGroupWrite: Writes[UserGroup] = (
      (JsPath \ "id").write[Long] and
      (JsPath \ "group").write[String]
    )(unlift(UserGroup.unapply))

    Ok(Json.toJson(userGroup)(userGroupWrite))
  }


  case class UserGroup2(id: Long, group: String)
  object UserGroup2 {
    // define how to serialize a UserGroup2 into json
    implicit val write: Writes[UserGroup2] = (
      (JsPath \ "id").write[Long] and
      (JsPath \ "group").write[String]
    )(unlift(UserGroup2.unapply))
  }

  def fromCaseClass3 = Action {
    val userGroup = UserGroup2(456l, "play user group")
    Ok(Json.toJson(userGroup))
  }


  case class UserGroup3(id: Long, group: String)
  object UserGroup3 {
    // define how to serialize a UserGroup3 into json
    implicit val write = Json.writes[UserGroup3]
  }

  def fromCaseClass4 = Action {
    val userGroup = UserGroup3(456l, "play user group")
    Ok(Json.toJson(userGroup))
  }


  case class Member(name: String, userGroup: UserGroup3)
  object Member{
    implicit val write = Json.writes[Member]
  }

  def fromCaseClass5 = Action {
    val member = Member("Yann", UserGroup3(456l, "play user group"))
    Ok(Json.toJson(member))
  }

}