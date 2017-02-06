package mehbat.core

trait User
case class Person(userId: String, name: String) extends User{
	require(!userId.isEmpty && !name.isEmpty)
}
case object Anonym extends User
