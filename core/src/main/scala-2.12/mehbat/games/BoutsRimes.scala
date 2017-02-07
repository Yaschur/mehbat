package mehbat.games

import mehbat.core.Game

case class Line(author: String, text: String)

class BoutsRimes extends Game {
	private var lines: Vector[Line] = Vector.empty

	def currentPlayer: Option[String] = {
		if (participants.isEmpty) None
		else {
			lazy val evenMode = lines.length % 2 == 0
			lines match {
				case Vector() => Some(participants.head)
				case _ :+ Line(a, _) if evenMode => Some(a)
				case _ =>
					val ind = participants.indexWhere(_ == lines.last.author)
					Some(participants((ind + 1) % participants.length))
			}
		}
	}
	private[mehbat] def addLine(author: String, text: String): Unit = {
		assert(!author.isEmpty && !text.isEmpty)
		if (currentPlayer.getOrElse("") == author)
			lines = lines :+ Line(author, text)
	}
	private[mehbat] def getLines(userId: String): List[Line] = {
		assert(!userId.isEmpty)
		if (currentPlayer.getOrElse("") == userId)
			lines match {
				case Vector() => List()
				case _ :+ l1 :+ l2 if l2.author == userId => List(l1, l2)
				case _ => List(lines.last)
			}
		else Nil
	}

	override protected def prepareGameFor(userId: String): Unit = ()

	override protected def clearGameFor(userId: String): Unit =
		lines match {
			case _ :+ Line(a1, _) :+ Line(a2, _) if a2 == userId && a1 != a2 =>	lines = lines.init
			case _ =>
		}
}
