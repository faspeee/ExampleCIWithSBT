name := "SbtWithCI"
version := "0.1"
scalaVersion := "2.13.2"

lazy val akkaHttpVersion = "10.1.12"
lazy val akkaVersion    = "2.6.5"
enablePlugins(JavaAppPackaging)
enablePlugins(DockerPlugin)

lazy val client = project.settings(
  mainClass := Some("MainClient"),
  name := "budrische-client-scala",
  libraryDependencies ++= Seq(
    libraries.akkaHttp,
    libraries.akkaActor,
    libraries.akkaStream,
    libraries.sprayJson,
  ),
  scalacOptions ++= compilerOptions,
  assemblySettings
).dependsOn(
  utils,
  server)

lazy val server = project.settings(

  dockerBaseImage       := "openjdk:jre",
  dockerExposedPorts := Seq(8080),
  mainClass := Some("main.MainServer"),
  name := "budrische-server-scala",
  libraryDependencies ++= Seq(
    libraries.akkaHttp,
    libraries.akkaActor,
    libraries.akkaStream,
    libraries.sprayJson,
    libraries.slick,
    libraries.slickHikaricp,
    libraries.jwt,
    "org.scala-lang"          % "scala-reflect"             % scalaVersion.value,
    libraries.mssql,
    libraries.logBack
  ),
  scalacOptions ++= compilerOptions,
  assemblySettings
).dependsOn(utils)

lazy val utils = project.settings(
  name := "budrische-util-scala",
  libraryDependencies ++= Seq(
    libraries.sprayJson,
    libraries.akkaActor,
    libraries.akkaStream
  ),
  scalacOptions ++= compilerOptions,
  assemblySettings
)


lazy val compilerOptions = Seq(
  "-encoding","utf8",
  "-explaintypes",
  "-deprecation",
  "-unchecked",
  "-Xfuture",
  "-Ywarn-dead-code",
  "-Ylog-classpath"
)

lazy val libraries = new {
  val akkaHttpVersion = "10.1.12"
  val akkaVersion = "2.6.5"
  val logBackVersion = "1.2.3"
  val scalaFxVersion = "0.5"
  val slickVersion = "3.3.2"
  val mssqlVersion = "8.2.2.jre8"
  val jwtVersion = "4.2.0"
  val scalaReflectVersion = "2.13.2"

  val akkaHttp       = "com.typesafe.akka"        %% "akka-http"                % akkaHttpVersion
  val akkaActor      ="com.typesafe.akka"         %% "akka-actor-typed"         % akkaVersion
  val akkaStream     = "com.typesafe.akka"        %% "akka-stream"              % akkaVersion
  val logBack        = "ch.qos.logback"           % "logback-classic"           % logBackVersion
  val sprayJson      = "com.typesafe.akka"        %% "akka-http-spray-json"     % akkaHttpVersion
  val scalaFx        = "org.scalafx"              %% "scalafxml-core-sfx8"      % scalaFxVersion
  val slick          = "com.typesafe.slick"       %% "slick"                    % slickVersion
  val slickHikaricp  = "com.typesafe.slick"       %% "slick-hikaricp"           % slickVersion
  val mssql         = "com.microsoft.sqlserver"  % "mssql-jdbc"                 % mssqlVersion
  val scalaReflect   =  "org.scala-lang"          % "scala-reflect"             % scalaReflectVersion
  val jwt            = "com.pauldijou"            %% "jwt-spray-json"           % jwtVersion
}

lazy val assemblySettings = Seq(
  assemblyJarName in assembly := name.value + ".jar",
  assemblyMergeStrategy in assembly := {
    case PathList("META-INF", xs @ _*) => MergeStrategy.discard
    case PathList("application.conf")  => MergeStrategy.concat
    case PathList("reference.conf")    => MergeStrategy.concat
    case x                             => MergeStrategy.first
  }
)