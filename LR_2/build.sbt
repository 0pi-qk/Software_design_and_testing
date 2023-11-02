// Name of the project
name := "lab_2"

// Project version
version := "19.0.0-R30"

// Version of Scala used by the project
scalaVersion := "2.13.9"

// Add dependency on ScalaFX library
libraryDependencies += "org.scalafx" %% "scalafx" % "19.0.0-R30"

scalacOptions ++= Seq("-unchecked", "-deprecation", "-encoding", "utf8", "-feature")


// Fork a new JVM for 'run' and 'test:run', to avoid JavaFX double initialization problems
fork := true

