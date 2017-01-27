package mehbat.core

class Gamer(user: User, game: Game) {
	def enterGame(): Unit = game.addGamer(user.userId, user.userId)
	def exitGame(): Unit = game.removeGamer(user.userId, user.userId)
	def isInGame: Boolean = game.isInGame(user.userId)
}
