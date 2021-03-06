import mill._, scalalib._

trait Scala3Module extends ScalaModule {
  def scalaVersion = "3.0.0-RC1"
}

object game extends Scala3Module {
  def publishVersion = "0.1.0"

  object test extends Scala3Module with TestModule {
    def testFrameworks = Seq("utest.runner.Framework")
    def ivyDeps = Agg(ivy"com.lihaoyi::utest::0.7.7")
  }
}

