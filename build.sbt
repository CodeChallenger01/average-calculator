ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

lazy val root = (project in file("."))
  .settings(
    name := "assignment-1"
  )
libraryDependencies += "au.com.bytecode" % "opencsv" % "2.4"
libraryDependencies += "com.github.tototoshi" %% "scala-csv" % "1.3.10"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.15" % "test"