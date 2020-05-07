name := "SbtWithCI"

version := "0.1"

scalaVersion := "2.13.2"
libraryDependencies += "org.scalatest" % "scalatest_2.13" % "3.1.1" % "test"

libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % "3.3.2",
  "org.slf4j" % "slf4j-nop" % "1.6.4",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.3.2",
  "com.microsoft.sqlserver" % "mssql-jdbc" % "8.2.2.jre8"
)
libraryDependencies += "org.scala-lang.modules" %% "scala-async" % "0.10.0"
libraryDependencies += "org.scala-lang" % "scala-reflect" % scalaVersion.value % Provided
