name := "core"

version := "1.0"

scalaVersion := "2.12.1"

libraryDependencies ++= Seq(
	"org.scalactic" %% "scalactic" % "3.0.1",
	"org.scalatest" %% "scalatest" % "3.0.1" % "test",
	"org.scala-lang" % "scala-reflect" % "2.12.1",
	"org.scala-lang.modules" %% "scala-xml" % "1.0.5"
)
    