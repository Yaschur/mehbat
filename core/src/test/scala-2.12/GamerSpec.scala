import mehbat.core.{Game, Gamer, User}
import org.scalatest.FlatSpec

class GamerSpec extends FlatSpec {

	"A Gamer" can "add himself to game" in {
		val game = getGame
		val user = User("abc", "xyz")
		val gamer = new Gamer(user, game)

		gamer.enterGame()

		assert(gamer.isInGame)
	}

	it must "not be in game without enter" in {
		val game = getGame
		val user = User("abc", "xyz")

		val gamer = new Gamer(user, game)

		assert(!gamer.isInGame)
	}

	private def getGame = new Game {
		override def clearGameFor(userId: String): Unit = ()
		override protected def prepareGameFor(userId: String): Unit = ()
	}
}
