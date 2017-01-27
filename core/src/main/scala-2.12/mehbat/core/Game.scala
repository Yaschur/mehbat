package mehbat.core

trait Game {
	protected var participants: Vector[String] = Vector.empty

	protected def prepareGameFor(userId: String)
	protected def clearGameFor(userId: String)

	private[mehbat] def addGamer(who: String, whom: String): Unit = {
		assert(!who.isEmpty && !whom.isEmpty)
		if (!isInGame(whom)) {
			participants = participants :+ whom
			prepareGameFor(whom)
		}
	}
	private[mehbat] def removeGamer(who: String, whom: String): Unit = {
		assert(!who.isEmpty && !whom.isEmpty)
		if (isInGame(whom)) {
			clearGameFor(whom)
			participants = participants filterNot whom.==
		}
	}
	private[mehbat] def isInGame(userId: String): Boolean =
		participants.contains(userId)
}
