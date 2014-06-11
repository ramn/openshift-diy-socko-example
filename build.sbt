import com.typesafe.sbt.SbtNativePackager._
import NativePackagerKeys._

name := "diy-sbt"

version := "1.0"

scalaVersion := "2.11.1"

packageArchetype.java_application

libraryDependencies += "org.mashupbots.socko" %% "socko-webserver" % "0.5.0"
