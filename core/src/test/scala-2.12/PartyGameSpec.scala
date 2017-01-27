import mehbat.Party
import org.scalatest.FlatSpec

class PartyGameSpec extends FlatSpec{

	"A Party" should "have no starting lines" in {
		val party = new Party

		assertResult(0)(party.stats.lines)
	}

	it should "return empty lines on start" in {
		val party = new Party
		val user = "userId"
		party.addPlayer(user)

		assertResult(0)(party.getLinesFor(user).length)
	}

	it should "add a line for first user on start" in {
		val party = new Party
		val (u1, u2, u3) = ("user1", "user2", "user3")
		val txt = "text line"
		party.addPlayer(u1)
		party.addPlayer(u2)
		party.addPlayer(u3)

		party.addLine(u1, txt)

		assertResult(1)(party.stats.lines)
	}

	it should "not add a line from not first user on start" in {
		val party = new Party
		val (u1, u2, u3) = ("user1", "user2", "user3")
		val txt = "text line"
		party.addPlayer(u1)
		party.addPlayer(u2)
		party.addPlayer(u3)

		party.addLine(u2, txt)
		party.addLine(u3, txt)

		assertResult(0)(party.stats.lines)
	}
}
