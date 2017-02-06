package mehbat.core

class Gamer(user: User, game: Game) {
	def enterGame(): Unit =
		user match {
			case Person(userId, _) => game.addGamer (userId, userId)
			case Anonym =>
		}
	def exitGame(): Unit =
		user match {
			case Person(userId, _) => game.removeGamer(userId, userId)
			case Anonym =>
		}
	def isInGame: Boolean =
		user match {
			case Person(userId, _) => game.isInGame(userId)
			case Anonym => false
		}
}
