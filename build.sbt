/*
    mandyville modelling - build.sbt
 */

ThisBuild / scalaVersion := "2.13.8"
ThisBuild / organization := "es.odavi.mandyville"

val testVersion = "3.2.7"
val mockVersion = "1.16.42"
val scalaTic = "org.scalactic"  %% "scalactic"     % testVersion
val scalaTest = "org.scalatest" %% "scalatest"     % testVersion
val mockito = "org.mockito"     %% "mockito-scala" % mockVersion

val quillVersion = "3.7.1"

val quill = Seq(
  "io.getquill" %% "quill-async-postgres",
  "io.getquill" %% "quill-jdbc"
).map(_ % quillVersion)

val pgVersion = "42.2.24"
val pg = "org.postgresql" % "postgresql" % pgVersion

val dIVersion = "0.9.9"
val dockerIt = Seq(
  "com.whisk" %% "docker-testkit-scalatest",
  "com.whisk" %% "docker-testkit-impl-spotify"
).map(_ % dIVersion)

val flywayVersion = "7.8.1"
val flyway = "org.flywaydb" % "flyway-core" % flywayVersion

val sLVersion = "3.9.4"
val scalaLogging = "com.typesafe.scala-logging" %% "scala-logging" % sLVersion

val logbackVersion = "1.2.7"
val logback = "ch.qos.logback" % "logback-classic" % logbackVersion

IntegrationTest / envVars := Map(
  "DOCKER_HOST" -> "unix:///var/run/docker.sock"
)

lazy val settings = (project in file("."))
  .configs(IntegrationTest)
  .settings(
    name := "Mandyville Modelling",
    Defaults.itSettings,
    libraryDependencies += scalaTic,
    libraryDependencies += scalaTest % "it,test",
    libraryDependencies += mockito   % Test,
    libraryDependencies ++= quill,
    libraryDependencies += pg,
    libraryDependencies ++= dockerIt,
    libraryDependencies += flyway,
    libraryDependencies += scalaLogging,
    libraryDependencies += logback,
    libraryDependencies += "com.sun.activation" % "javax.activation" % "1.2.0",
    scalacOptions ++= Seq("-deprecation", "-feature"),
  )

inConfig(IntegrationTest)(org.scalafmt.sbt.ScalafmtPlugin.scalafmtConfigSettings)

IntegrationTest / parallelExecution := false