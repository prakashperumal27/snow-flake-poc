ThisBuild / version := "0.1.0-SNAPSHOT"

scalaVersion := "2.12.13"

lazy val root = (project in file("."))
  .settings(
    name := "snowflake-kafka"
  )

/** read data from snowflake database */
libraryDependencies += "com.snowflake" % "snowpark" % "1.9.0"

/** convert scala case class to json */
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.9.2"

/** sending transformed data to kafka producer */
libraryDependencies += "io.confluent" % "kafka-avro-serializer" % "6.0.1"
libraryDependencies += "com.typesafe.play" %% "play-ws" % "2.8.9"

/** application logging */
libraryDependencies ++= Seq(
    "org.slf4j" % "slf4j-api" % "1.7.32",
    "ch.qos.logback" % "logback-classic" % "1.2.6"
)


resolvers += "Confluent Maven Repo" at "https://packages.confluent.io/maven/"
