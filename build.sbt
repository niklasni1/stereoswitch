organization := "dk.cirque.stereoswitch"

name := "stereoswitch"

version := "0.1-SNAPSHOT"

libraryDependencies ++= Seq(
  "io.spray" % "spray-can" % "1.2.0",
  "io.spray" % "spray-routing" % "1.2.0",
  "com.typesafe.akka" %% "akka-actor" % "2.2.3",
  "com.typesafe.akka" %% "akka-kernel" % "2.2.3",
  "org.json4s" %% "json4s-native" % "3.2.7",
  "org.json4s" %% "json4s-ext" % "3.2.7",
  "org.specs2" %% "specs2" % "2.3.8" % "test",
  "io.spray" % "spray-testkit" % "1.2.0" % "test",
  "com.typesafe.akka" %% "akka-testkit" % "2.2.3" % "test"
)
