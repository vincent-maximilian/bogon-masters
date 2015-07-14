import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._
import play.api.test.Helpers._

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
@RunWith(classOf[JUnitRunner])
class ApplicationSpec extends Specification {

  "Application" should {

    "send 404 on a bad request" in new WithApplication{
      route(FakeRequest(GET, "/boum")) must beSome.which (status(_) == NOT_FOUND)
    }

    "respond to the version API" in new WithApplication{
      val versionRes = route(FakeRequest(GET, "/")).get

      status(versionRes) must equalTo(OK)
      contentType(versionRes) must beSome.which(_ == "application/json")
      // TODO: Validate JSON
      // contentAsJson(versionRes) must /("name" -> "Bogon Masters!")
      //        and /("version" -> "0.1")
    }

    "render the client app page" in new WithApplication() {
      val client = route(FakeRequest(GET, "/client")).get

      status(client) must equalTo(OK)
      contentType(client) must beSome.which(_ == "text/html")
      contentAsString(client) must contain("Master the Bogons!")
    }
  }
}
