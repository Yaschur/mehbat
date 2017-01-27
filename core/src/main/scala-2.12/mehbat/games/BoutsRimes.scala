package mehbat.games

import mehbat.core.Game

class BoutsRimes extends Game {
	private var lines: Vector[Line] = Vector.empty

	override protected def prepareGameFor(userId: String): Unit = ()

	override protected def clearGameFor(userId: String): Unit =
		lines match {
			case _ :+ Line(a1, _) :+ Line(a2, _) if a2 == userId && a1 != a2 =>	lines = lines.init
			case _ =>
		}

	case class Line(author: String, text: String)
}
