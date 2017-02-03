name := "core"

version := "1.0"

scalaVersion := "2.12.1"

libraryDependencies ++= Seq(
	"org.scalactic" %% "scalactic" % "3.0.1",
	"org.scalatest" %% "scalatest" % "3.0.1" % "test",
	"org.scala-lang" % "scala-reflect" % "2.12.1",
	"org.scala-lang.modules" %% "scala-xml" % "1.0.5",

	"com.typesafe.akka" %% "akka-http" % "10.0.3",
	"com.typesafe.akka" %% "akka-http-spray-json" % "10.0.3",
	"ch.megard" %% "akka-http-cors" % "0.1.11"
)
    