package controllers

import javax.inject._
import model.UserSegmentation
import play.api.libs.json._
import play.api.mvc._

import scala.concurrent.Future

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(
  cc: ControllerComponents) extends AbstractController(cc) {

  private[this] val defaultLength = 1 << 22

  /**
   * Create an Action to render an HTML page with a welcome message.
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def create(): Action[JsValue] = Action.async(parse.tolerantJson(defaultLength)) { implicit request =>
    val a = request.body.validate[Req].get
    Future.successful(Ok(Json.obj("status" -> "ok", "message" -> a)))
  }

}

case class Req(id: Long, name: String)

object Req {
  implicit val format: OFormat[Req] = Json.format[Req]
}
