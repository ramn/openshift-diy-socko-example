import org.mashupbots.socko.routes.Routes
import org.mashupbots.socko.routes.GET
import org.mashupbots.socko.infrastructure.Logger
import org.mashupbots.socko.webserver.WebServer
import org.mashupbots.socko.webserver.WebServerConfig

import akka.actor.ActorSystem
import akka.actor.Props


object Server extends Logger {
  val actorSystem = ActorSystem("system")

  val routes = Routes {
    case GET(request) => {
      actorSystem.actorOf(Props[HelloHandler]) ! request
    }
  }

  def main(args: Array[String]) {
    val ip = sys.env.getOrElse("OPENSHIFT_DIY_IP", "127.0.0.1")
    val port: Int = sys.env.getOrElse("OPENSHIFT_DIY_PORT", "8080").toInt
    val config = WebServerConfig(hostname=ip, port=port)
    val webServer = new WebServer(config, routes, actorSystem)
    webServer.start()

    Runtime.getRuntime.addShutdownHook(new Thread {
      override def run { webServer.stop() }
    })

    println(s"HTTP server started at $ip:$port")
  }
}
