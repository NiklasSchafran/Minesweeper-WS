import org.scoverage.coveralls.Imports.CoverallsKeys._

val scala3Version = "3.2.2"

lazy val root = project
  .in(file("."))
  .settings(
    name := "minesweeper",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,

    libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.14",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.14" % Test,
    libraryDependencies += "org.mockito" % "mockito-core" % "5.11.0" % Test,
    libraryDependencies += "org.scalatestplus" %% "mockito-3-4" % "3.2.9.0" % Test,
    libraryDependencies += "org.scala-lang.modules" %% "scala-swing" % "3.0.0",
    libraryDependencies += "com.google.inject" % "guice" % "5.0.1",
    libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "2.0.1",
    libraryDependencies += "com.typesafe.play" %% "play-json" % "2.10.0-RC5",
    libraryDependencies ++= Seq(
      "io.circe" %% "circe-core" % "0.14.5",
      "io.circe" %% "circe-generic" % "0.14.5",
      "io.circe" %% "circe-parser" % "0.14.5"
    )
  )
  .enablePlugins(ScoverageSbtPlugin)

coverageHighlighting := true
coverageFailOnMinimum := false
coverageMinimumStmtTotal := 80

// Coveralls-Einstellungen
coverallsTokenFile := sys.env.get("COVERALLS_REPO_TOKEN")
