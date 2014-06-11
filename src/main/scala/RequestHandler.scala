import org.mashupbots.socko.events.HttpRequestEvent
import akka.actor.Actor
import java.util.Date


class HelloHandler extends Actor {
  def receive = {
    case event: HttpRequestEvent =>
      event.response.write(s"Hello from Socko (${new Date()})")
      context.stop(self)
  }
}
