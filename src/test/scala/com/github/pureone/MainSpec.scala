package com.github.pureone

import org.scalatest._

class MainSpec extends FlatSpec with Matchers {
  "Main Object" should "say Hello World" in {
    Main.greeting shouldEqual "Hello World!"
  }
}
