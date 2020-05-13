name := "SbtWithCI"

version := "0.1"
scalacOptions += "-deprecation"
scalaVersion := "2.13.2"
scalacOptions += "-Ymacro-annotations"
libraryDependencies += "org.scalafx" %% "scalafxml-core-sfx8" % "0.5"

libraryDependencies += "org.scalatest" % "scalatest_2.13" % "3.1.1" % "test"

libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % "3.3.2",
  "org.slf4j" % "slf4j-nop" % "1.6.4",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.3.2",
  "com.microsoft.sqlserver" % "mssql-jdbc" % "8.2.2.jre8"
)
// https://mvnrepository.com/artifact/org.openjfx/javafx
libraryDependencies += "org.openjfx" % "javafx" % "11" pomOnly()
// https://mvnrepository.com/artifact/org.openjfx/javafx-controls
libraryDependencies += "org.openjfx" % "javafx-controls" % "11"
