package mehbat

class Participant(val userId: String) {
	require(!userId.isEmpty, "user can not be empty")

	private var active = true

	def pause(): Unit = active = false
	def resume(): Unit = active = true
	def isActive: Boolean = active
}
