name := """Tonari"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  "org.postgresql" % "postgresql" % "9.4-1201-jdbc41",
  "org.mindrot" % "jbcrypt" % "0.3m"
)

// FOR PRODUCTION, ENSURE YOU ARE USING LATEST VERSION OF BCRYPT LIB

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator

PlayKeys.fileWatchService := play.runsupport.FileWatchService.sbt(2000)