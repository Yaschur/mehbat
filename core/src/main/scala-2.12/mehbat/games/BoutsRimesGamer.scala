package mehbat.games

import mehbat.core.{Gamer, User}

class BoutsRimesGamer(user: User, game: BoutsRimes) extends Gamer(user, game) {
	def addLines(text: String): Unit = game.addLine(user.userId, text)
	def getLines: List[Line] = game.getLines(user.userId)
}
