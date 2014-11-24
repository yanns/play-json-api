import play.api.{Logger, GlobalSettings}
import play.api.libs.json.Json
import play.api.mvc.{Result, RequestHeader}
import play.api.mvc.Results._

import scala.concurrent.Future

object Global extends GlobalSettings {
  override def onError(request: RequestHeader, ex: Throwable): Future[Result] = {
    Logger.error(ex.getMessage, ex)
    Future.successful(InternalServerError(Json.obj("error" -> ex.getMessage)))
  }
}