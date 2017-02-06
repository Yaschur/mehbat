import mehbat.core.Person
import mehbat.games.{BoutsRimes, BoutsRimesGamer}
import org.scalatest.FlatSpec

class BoutsRimesGamerSpec extends FlatSpec {

	"A BouteRimesGamer" should "not be current player without entering" in {
		val game = new BoutsRimes
		val user = Person("abc", "xyz")

		new BoutsRimesGamer(user, game)

		assert(game.currentPlayer.getOrElse("") != user.userId)
	}

	it must "be current player if enter new game" in {
		val game = new BoutsRimes
		val user = Person("abc", "xyz")
		val gamer = new BoutsRimesGamer(user, game)

		gamer.enterGame()

		assert(game.currentPlayer.getOrElse("") == user.userId)
	}

}
