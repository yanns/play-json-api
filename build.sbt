name := """play-json-api"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.4"

libraryDependencies ++= Seq(
  "org.webjars" % "jquery" % "2.1.1",
  ws,
  javaWs
)
