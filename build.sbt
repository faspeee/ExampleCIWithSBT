name := "SbtWithCI"
version := "0.1"

ThisBuild /scalaVersion := "2.13.2"


lazy val client = project.settings(
  mainClass := Some("view.MainGianni"),
  name := "budrische-client-scala",
  libraryDependencies ++= Seq(
    libraries.akkaHttp,
    libraries.akkaActor,
    libraries.akkaStream,
    libraries.sprayJson
  ),
  scalacOptions ++= compilerOptions,
  assemblySettings

).dependsOn(utils,server)
lazy val server = project.settings(

  dockerBaseImage       := "openjdk:jre",
  dockerExposedPorts := Seq(8080),
  mainClass  := Some("main.MainServer"),
  name := "budrische-server-scala",
  libraryDependencies ++= Seq(
    libraries.akkaHttp,
    libraries.akkaActor,
    libraries.akkaStream,
    libraries.sprayJson,
    libraries.slick,
    libraries.slickHikaricp,
    libraries.scalaReflect,
    libraries.mssql,
    libraries.logBack,
    librariesTest.scalatest
  ),
  scalacOptions ++= compilerOptions,
  assemblySettings
).dependsOn(utils)
lazy val utils = project.settings(
  name := "budrische-util-scala",
  libraryDependencies ++= Seq(
    libraries.sprayJson,
    libraries.jwt,
    libraries.akkaActor,
    libraries.akkaStream
  ),
  scalacOptions ++= compilerOptions
)

lazy val compilerOptions = Seq(
  "-encoding","utf8",
  "-explaintypes",
  "-deprecation",
  "-unchecked",
  "-Xsource:2.13.0",
  "-Ywarn-dead-code",
)

lazy val libraries = new {
  val akkaHttpVersion = "10.1.12"
  val akkaVersion     = "2.6.5"
  val logBackVersion  = "1.2.3"
  val slickVersion    = "3.3.2"
  val mssqlVersion    = "8.2.2.jre8"
  val jwtVersion      = "4.2.0"
  val scalaReflectVersion = "2.13.2"
  val akkaHttp       = "com.typesafe.akka"        %% "akka-http"                % akkaHttpVersion
  val akkaActor      ="com.typesafe.akka"         %% "akka-actor-typed"         % akkaVersion
  val akkaStream     = "com.typesafe.akka"        %% "akka-stream"              % akkaVersion
  val logBack        = "ch.qos.logback"           % "logback-classic"           % logBackVersion
  val sprayJson      = "com.typesafe.akka"        %% "akka-http-spray-json"     % akkaHttpVersion
  val slick          = "com.typesafe.slick"       %% "slick"                    % slickVersion
  val slickHikaricp  = "com.typesafe.slick"       %% "slick-hikaricp"           % slickVersion
  val mssql          = "com.microsoft.sqlserver"   % "mssql-jdbc"               % mssqlVersion
  val scalaReflect   = "org.scala-lang"            % "scala-reflect"            % scalaReflectVersion
  val jwt            = "com.pauldijou"            %% "jwt-spray-json"           % jwtVersion
}

lazy val librariesTest = new {
  val scalatestVersion = "3.3.0-SNAP2"
  val scalatestOrg = "org.scalatest"
  val scalatest =  "org.scalatest" %% "scalatest" % scalatestVersion % Test

}

lazy val assemblySettings = Seq(
  assemblyJarName in assembly := name.value + ".jar",
  excludeDependencies ++= Seq(librariesTest.scalatestOrg)
)
