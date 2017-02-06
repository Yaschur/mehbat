package mehbat.games

import mehbat.core.{Anonym, Gamer, Person, User}

class BoutsRimesGamer(user: User, game: BoutsRimes) extends Gamer(user, game) {
	def addLines(text: String): Unit =
		user match {
			case Person(userId, _) => game.addLine(userId, text)
			case Anonym =>
		}
	def getLines: List[Line] =
		user match {
			case Person(userId, _) => game.getLines(userId)
			case Anonym => List()
		}
}
