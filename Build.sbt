import org.scoverage.coveralls.Imports.CoverallsKeys._

val scala2Version = "2.13.12"

lazy val root = project
  .in(file("."))
  .settings(
    name := "minesweeper",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := scala2Version,

    libraryDependencies ++= Seq(
      "org.scalactic" %% "scalactic" % "3.2.18",
      "org.scalatest" %% "scalatest" % "3.2.18" % Test,

      "org.mockito" % "mockito-core" % "5.11.0" % Test,
      "org.scalatestplus" %% "mockito-4-11" % "3.2.18.0" % Test,

      "org.scala-lang.modules" %% "scala-swing" % "3.0.0",
      "org.scala-lang.modules" %% "scala-xml" % "2.2.0",

      "com.google.inject" % "guice" % "5.0.1",
      "com.typesafe.play" %% "play-json" % "2.10.5",

      "io.circe" %% "circe-core" % "0.14.6",
      "io.circe" %% "circe-generic" % "0.14.6",
      "io.circe" %% "circe-parser" % "0.14.6"
    )
  )
  .enablePlugins(ScoverageSbtPlugin)

coverageHighlighting := true
coverageFailOnMinimum := false
coverageMinimumStmtTotal := 80

coverallsTokenFile := sys.env.get("COVERALLS_REPO_TOKEN")
