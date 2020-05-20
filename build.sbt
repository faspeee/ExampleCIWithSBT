name := "SbtWithCI"

version := "0.1"
scalacOptions += "-deprecation"
scalaVersion := "2.13.2"
scalacOptions += "-Ymacro-annotations"


lazy val akkaHttpVersion = "10.1.12"
lazy val akkaVersion    = "2.6.5"
enablePlugins(JavaAppPackaging)
enablePlugins(DockerPlugin)
lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization    := "main",
      scalaVersion    := "2.13.1"
    )),

    dockerBaseImage       := "openjdk:jre",
    dockerExposedPorts := Seq(8080),
    mainClass in Compile := Some("main.InitAkkaHttp"),
    name := "akka-http-quickstart-scala",
    libraryDependencies ++= Seq(
      "com.typesafe.akka"  %% "akka-http"                % akkaHttpVersion,
      "com.typesafe.akka"  %% "akka-actor-typed"          % akkaVersion,
      "com.typesafe.akka"  %% "akka-stream"              % akkaVersion,
      "ch.qos.logback"     % "logback-classic"           % "1.2.3",
      "com.typesafe.akka"  %% "akka-http-spray-json"     % akkaHttpVersion,
      "com.typesafe.slick" %% "slick"                    % "3.3.2",
      "org.slf4j"          % "slf4j-nop"                 % "1.6.4",
      "com.typesafe.slick" %% "slick-hikaricp"           % "3.3.2",
      "com.microsoft.sqlserver" % "mssql-jdbc"           % "8.2.2.jre8",
      "org.scalafx"        %% "scalafxml-core-sfx8"      % "0.5",
      "org.openjfx"        % "javafx-controls"           % "11",
      "org.openjfx"        % "javafx"                    % "11" pomOnly(),
      "org.scala-lang"     % "scala-reflect"             % scalaVersion.value,
      "com.typesafe.akka" %% "akka-http-testkit"         % akkaHttpVersion % Test,
      "org.scalatest" % "scalatest_2.13" %      "3.1.1"          % Test

    )
  )

