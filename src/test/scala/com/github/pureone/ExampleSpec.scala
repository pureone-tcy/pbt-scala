package com.github.pureone

import org.scalatest._

class ExampleSpec extends FlatSpec with Matchers {
  "Culc Test" should "Plus Result Safe" in {
    val num = 1 + 1
    assert(num == 2)
  }

  it should "減算結果が正しいこと" in {
    val a = 5
    val b = 2

    assertResult(3) {
      a - b
    }
  }
}
