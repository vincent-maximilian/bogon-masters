package controllers

import play.api._
import play.api.libs.iteratee.{Iteratee, Concurrent}
import play.api.mvc._
import play.api.libs.json.Json
import scala.concurrent.ExecutionContext.Implicits.global


class Application extends Controller {

  def index =  WebSocket.using[String] { request =>
    //Concurernt.broadcast returns (Enumerator, Concurrent.Channel)

    val (out,channel) = Concurrent.broadcast[String]
    //log the message to stdout and send response back to client

    val in = Iteratee.foreach[String] {
      msg => println(msg)
        //the channel will push to the Enumerator
        channel push("RESPONSE: " + msg + " you sucka")
    }
    (in,out)
  }

  // Server up client-side app code
  def client = Action {
    Ok(views.html.client("This is your life Charlie Brown"))
  }

  def version = Action {
    Ok(Json.toJson(Map(
      "name" -> "Bogon Masters!",
      "version" -> "0.1"
    )))
  }

  def hello(name: String) = Action {
    Ok("Hello " + name)
  }

  def id(id: Long) = Action {
    Ok("Howdy " + id)
  }
}
