import Dependencies._

ThisBuild / scalaVersion     := "2.12.8"
ThisBuild / version          := "0.0.1"
ThisBuild / organization     := "com.github.pureone"
ThisBuild / organizationName := "pureone"

lazy val root = (project in file("."))
  .settings(
    name := "pbt-scala",
    parallelExecution in Test := false,
    libraryDependencies ++= dependencies
  )
