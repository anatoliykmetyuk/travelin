import mill._, scalalib._

object game extends ScalaModule {
  def scalaVersion = "3.0.0-RC1"
  def publishVersion = "0.1.0"

  object test extends ScalaModule with TestModule with Tests {
    def testFrameworks = Seq("utest.runner.Framework")
    def ivyDeps = Agg(ivy"com.lihaoyi::utest::0.7.7")
  }
}

