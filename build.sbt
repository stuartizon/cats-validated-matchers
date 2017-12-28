name := "cats-validated-matchers"
organization := "com.stuartizon"
version := "1.0.0"
scalaVersion := "2.11.12"

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-core" % "1.0.0",
  "org.specs2" %% "specs2-core" % "3.8.7",
  "org.specs2" %% "specs2-mock" % "3.8.7"
)

licenses += ("MIT", url("https://opensource.org/licenses/MIT"))