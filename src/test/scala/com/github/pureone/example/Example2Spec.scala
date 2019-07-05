package com.github.pureone.example

import com.github.pureone.UnitSpec
import org.scalatest.prop.GeneratorDrivenPropertyChecks

class Example2Spec extends UnitSpec with GeneratorDrivenPropertyChecks {

  "Generator" should "Int Value Zero Multiplication" in {
    forAll { num: Int =>
//      println(s"num=$num")
      assert(num * 0 == 0)
    }
  }

  it should "whenever" in {
    forAll { num: Int =>
//      println(s"before whenever num=$num")
      whenever(num > 2) {
//        println(s"after whenever num=$num")
        assert(num > 2)
      }
    }
  }

  it should "List Size" in {
    forAll { (l1: List[Int], l2: List[Int]) =>
      //      println(s"List1=${l1.size}, List2=${l2.size}")
      assert(l1.size + l2.size == (l1 ::: l2).size)
    }
  }

  it should "Error arguments names" in {
    forAll("List1","ListB") { (l1: List[Int], l2: List[Int]) =>
      assert(l1.size + l2.size == (l1 ::: l2).size)
//      assert(l1.size + l2.size == 10)
    }
  }

  import org.scalacheck.Gen

  "ScalaCheck Gen" should "Custom Gen" in {
    val numGen: Gen[Int] = Gen.choose(-1000, 1000)
    forAll (numGen) { num =>
      num * 2 % 2 should equal (0)
    }
  }

  it should "for expression" in {
    val numGen = for {
      x <- Gen.choose(-1000, 1000)
    } yield x * 2

    forAll(numGen) { num =>
      num % 2 should equal (0)
    }
  }

  it should "Pos > Neg" in {
    forAll(Gen.posNum[Long], Gen.negNum[Long]) { (posNumLong, negNumLong) =>
//      println(s"pos=$posNumLong, neg=$negNumLong")
      assert(posNumLong > negNumLong)
    }
  }

  it should "Pos > Neg and argument names" in {
    forAll(
      (Gen.posNum[Long], "pos"),
      (Gen.negNum[Long], "neg")) { (posNumLong, negNumLong) =>
      assert(posNumLong > negNumLong)
//      assert(posNumLong < negNumLong)
    }
  }

  case class Person(name: String, age: Int) {
    def getIntroduction: String = s"My name is $name. I am $age years old."
  }

  it should "case class" in {

    val person = for {
      name <- Gen.oneOf("Taro","Hanako", "Ken", "Bob")
      age <- Gen.posNum[Int]
    } yield Person(name, age)

    forAll(person) { p =>
      val expected = s"My name is ${p.name}. I am ${p.age} years old."
      assert(p.getIntroduction == expected)
    }
  }
}
