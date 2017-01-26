package mehbat

class Party {
	private var participants: Vector[Participant] = Vector.empty
	private var lines: Vector[Line] = Vector.empty
	private val playerIndex: Int = 0

	// participation
	def addPlayer(userId: String): Unit =
		if (!participants.exists(_.userId == userId))
			participants = participants :+ new Participant(userId)
	def resumePlayer(userId: String): Unit =
		participants.find(p => p.userId == userId && !p.isActive).foreach(_.resume())
	def pausePlayer(userId: String): Unit =
		participants.find(p => p.userId == userId && p.isActive).foreach(_.pause())

	def getPlayers(activeOnly: Boolean = false): Vector[String] =
		participants.filter(!activeOnly || _.isActive).map(_.userId)

	def currentPlayer: String = participants(playerIndex).userId

	// game
	//def getResult: Vector[String] = lines
	def getLinesFor(userId: String): Vector[Line] =
		Vector.empty
	def addLine(userId: String, text: String): Unit = {
		if (currentPlayer == userId) lines = lines :+ Line(userId, text)
	}

	// stat
	def stats = Stats(participants.count(_.isActive), lines.length)

	case class Stats(players: Int, lines: Int)
	case class Line(author: String, text: String)
}
