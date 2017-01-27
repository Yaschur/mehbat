package mehbat.core

import mehbat.Participant

trait Game {
	private var participants: Vector[Participant] = Vector.empty

	protected def prepareGameFor(userId: String)
	protected def clearGameFor(userId: String)

	private[mehbat] def addGamer(who: String, whom: String): Unit = {
		if (!isInGame(whom)) {
			participants = participants :+ new Participant(whom)
			prepareGameFor(whom)
		}
	}
	private[mehbat] def removeGamer(who: String, whom: String): Unit = {
		if (isInGame(whom)) {
			clearGameFor(whom)
			participants = participants filterNot whom.==
		}
	}
	private[mehbat] def isInGame(userId: String): Boolean =
		participants.exists(_.userId == userId)
}
