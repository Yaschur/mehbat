package mehbat.api

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.Http.ServerBinding
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model._
import akka.http.scaladsl.model.headers.{Authorization, OAuth2BearerToken}
import akka.http.scaladsl.server.{Directive, Directive0, Directive1}
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import authentikat.jwt.JsonWebToken
import ch.megard.akka.http.cors.CorsDirectives._
import mehbat.core.{Anonym, Game, Person, User}
import mehbat.games.{BoutsRimes, BoutsRimesGamer}
import spray.json.DefaultJsonProtocol

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import spray.json.DefaultJsonProtocol._

import scala.concurrent.Future
import scala.io.StdIn

object WebServer {
	def main(args: Array[String]): Unit = {
		implicit val system = ActorSystem()
		implicit val materializer = ActorMaterializer()

		//// needed for the future onFailure in the end
		//implicit val executionContext = system.dispatcher

		val game: BoutsRimes = new BoutsRimes
		final case class GameOut(lines: Seq[String])
		final case class GameIn(line: String)
		implicit val gameOutFormat = jsonFormat1(GameOut)
		implicit val gameInFormat = jsonFormat1(GameIn)

		def getGamer(user: User): BoutsRimesGamer = {
			val gamer = new BoutsRimesGamer(user, game)
			if (!gamer.isInGame) gamer.enterGame()
			gamer
		}
		def getOut(user: User): GameOut = {
			GameOut(getGamer(user).getLines.map(_.text))
		}
		def getIn(user: User, line: String): Unit = {
			getGamer(user).addLines(line)
		}

		val extractUser: Directive1[User] =
			extractCredentials.map {
				case Some(OAuth2BearerToken(JsonWebToken(_, claimsSet, _))) =>
					Person(claimsSet.asSimpleMap.get("sub"), "NoName")
				case _ => Anonym
			}

		val router =
			extractUser { user =>
				path("game") {
					cors() {
						get {
							complete {
								getOut(user)
							}
						}~
						post {
							entity(as[GameIn]) { game =>
								getIn(user, game.line)
								complete(StatusCodes.OK)
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
