package mehbat.core

case class User(userId: String, name: String){
	require(!userId.isEmpty && !name.isEmpty)
}
