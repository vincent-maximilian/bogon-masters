package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json.Json

class Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def version = Action {
    Ok(Json.toJson(Map(
      "name" -> "Bogon Masters!",
      "version" -> "0.1"
    )))
  }
}
