name := "dinotech"

version := "0.1"

scalaVersion := "2.12.11"

val sparkV = "2.4.5"

libraryDependencies ++= Seq(

	"org.apache.spark" %% "spark-sql" % sparkV % "provided",

	"org.scalatest" %% "scalatest" % "3.2.0" % "test"

)