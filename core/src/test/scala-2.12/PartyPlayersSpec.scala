import mehbat.Party
import org.scalatest.FlatSpec

class PartyPlayersSpec extends FlatSpec {

	"A Party" should "have no player after creating" in {
		val party = new Party

		assertResult(0)(party.stats.players)
	}

	it should "add player" in {
		val party = new Party
		val user = "abc"

		party.addPlayer(user)
		val players = party.getPlayers()

		assertResult(1)(players.length)
		assertResult(user)(players(0))
	}

	it should "add player in order" in {
		val party = new Party
		val (u1, u2, u3) = ("abc", "xcv", "err")

		party.addPlayer(u1)
		party.addPlayer(u2)
		party.addPlayer(u3)
		val players = party.getPlayers()

		assertResult(3)(players.length)
		assertResult(u1)(players(0))
		assertResult(u2)(players(1))
		assertResult(u3)(players(2))
	}

	it should "not add already added player" in {
		val party = new Party
		val (u1, u2) = ("abc", "xcv")

		party.addPlayer(u1)
		party.addPlayer(u2)
		party.addPlayer(u1)
		val players = party.getPlayers()

		assertResult(2)(players.length)
		assertResult(u1)(players(0))
		assertResult(u2)(players(1))
	}

	it should "pause and resume players" in {
		val party = new Party
		val (u1, u2) = ("abc", "xcv")
		party.addPlayer(u1)
		party.addPlayer(u2)

		party.pausePlayer(u1)
		party.pausePlayer(u2)
		party.resumePlayer(u1)
		val activePlayers = party.getPlayers(activeOnly = true)

		assertResult(1)(activePlayers.length)
		assertResult(u1)(activePlayers(0))
	}
}
