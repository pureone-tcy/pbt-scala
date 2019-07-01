package com.github.pureone.example

import com.github.pureone.UnitSpec
import org.scalatest.Tag
import org.scalatest.tagobjects.Slow

class Example1Spec extends UnitSpec {

  "A Calculation" should "plus" in {
    val num = 1 + 2
    assert(num == 3)
  }

  it should "minus" in {
    val a = 5
    val b = 2
    assertResult(3) {
      a - b
    }
  }

  it should "error" in {
    val s = "e"
    assertThrows[IndexOutOfBoundsException] {
//    assertThrows[NoSuchElementException] {
        s.charAt(-1)
    }
  }

  "An Assertions" should "custom error message" in {
    val attempted = 2
    assert(attempted == 2, "Execution was attempted " + attempted + " times instead of 1 time")
//    assert(attempted == 1, "Execution was attempted " + attempted + " times instead of 1 time")
  }

  it should "succeed or cancel or fail" in {
//    succeed
//    cancel()
//    fail()
//    fail("I've got a bad feeling about this")
  }

  it should "try catch" in {
    val s = "e"
    try {
      s.charAt(-1)
    } catch {
      case _: IndexOutOfBoundsException => succeed
//      case _: NoSuchElementException => succeed
      case _: Throwable => fail("test failed !!!")
    }
  }

  it should "error message" in {
    val s = "e"
    val caught = intercept[IndexOutOfBoundsException] {
      s.charAt(-1)
    }
    assert(caught.getMessage == "String index out of range: -1")
  }

  it should "clue" in {
    withClue("this is a clue") {
      assertThrows[IndexOutOfBoundsException] {
//      assertThrows[NoSuchElementException] {
          "hi".charAt(-1)
      }
    }
  }

  ignore should "ignore" in {
    fail()
  }

  object DBTest extends Tag("com.github.pureone.example.ExampleSpec.DBTest")

  "Tags" must "tag" taggedAs Slow in {
    val num = 1 + 1
    assert(num == 2)
  }

  it must "tag2" taggedAs (DBTest, Slow)in {
    val num = 1 + 1
    assert(num == 2)
  }

  case class Fruit(id: Int, name: String)
  object Fruit {
    val fruit = Seq(
      Fruit(5, "orange"), Fruit(7, "banana"), Fruit(10, "apple"), Fruit(3, "melon")
    )
    def findAll: Seq[Fruit] = fruit
    def findById(id: Int): Seq[Fruit] = fruit.filter(_.id > id)
  }

  "Object" should "findAll" in {
    val expected = Seq(
      Fruit(5, "orange"),
      Fruit(7, "banana"),
      Fruit(10, "apple"),
      Fruit(3, "melon")
    )
    val actual = Fruit.findAll
    actual shouldEqual expected
  }

  it should "findById" in {
    val actual = Fruit.findById(4)
    actual.foreach { x =>
      x.id should be > 4
    }
  }
}
