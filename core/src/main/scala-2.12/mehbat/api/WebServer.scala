package mehbat.api

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.Http.ServerBinding
import akka.http.scaladsl.model._
import akka.http.scaladsl.model.headers.{Authorization, OAuth2BearerToken}
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer

import ch.megard.akka.http.cors.CorsDirectives._

import scala.concurrent.Future
import scala.io.StdIn

object WebServer {
	def main(args: Array[String]): Unit = {
		implicit val system = ActorSystem()
		implicit val materializer = ActorMaterializer()

		//// needed for the future onFailure in the end
		//implicit val executionContext = system.dispatcher

		val router =
			path("game") {
				cors() {
					get {
						extractCredentials { creds =>
							complete {
								creds match {
									case Some(OAuth2BearerToken(token)) => "Found it! -> " + token
									case _ => "No creds"
								}
							}
						}
					}
				}
			}

		val (host, port) = ("localhost", 8080)
		//val bindingFuture: Future[ServerBinding] =
		Http().bindAndHandle(router, host, port)
		println(s"Server online at http://localhost:8080/\n")
//		StdIn.readLine() // let it run until user presses return
//		bindingFuture
//			.flatMap(_.unbind()) // trigger unbinding from the port
//			.onComplete(_ => system.terminate()) // and shutdown when done
	}
}
