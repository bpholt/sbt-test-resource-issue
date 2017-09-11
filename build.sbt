name := "test-resources"

lazy val specs2Version = "3.9.5"
libraryDependencies ++= Seq(
  "org.specs2" %% "specs2-core" % specs2Version % Test
)

val app = project in file(".")
