package mehbat.api

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.Http.ServerBinding
import akka.http.scaladsl.model._
import akka.http.scaladsl.model.headers.{Authorization, OAuth2BearerToken}
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import authentikat.jwt.JsonWebToken
import ch.megard.akka.http.cors.CorsDirectives._
import mehbat.core.{Game, User}
import mehbat.games.{BoutsRimes, BoutsRimesGamer}

import scala.concurrent.Future
import scala.io.StdIn

object WebServer {
	def main(args: Array[String]): Unit = {
		implicit val system = ActorSystem()
		implicit val materializer = ActorMaterializer()

		//// needed for the future onFailure in the end
		//implicit val executionContext = system.dispatcher

		val game: BoutsRimes = new BoutsRimes
		def getGamer(userId: String): BoutsRimesGamer = {
			val gamer = new BoutsRimesGamer(User(userId, "Anonymous"), game)
			if (userId != "__none__" && !gamer.isInGame) gamer.enterGame()
			gamer
		}

		val router =
			path("game") {
				cors() {
					get {
						extractCredentials { creds =>
							val userId = creds match {
								case Some(OAuth2BearerToken(JsonWebToken(_, claimsSet, _))) =>
									claimsSet.asSimpleMap.get.get("sub").toString
								case _ => "__none__"
							}
							val gamer = getGamer(userId)
							complete {
								gamer.getLines.map(_.text).toString
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
