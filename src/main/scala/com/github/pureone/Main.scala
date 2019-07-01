package com.github.pureone

object Main extends Greeting with App {
  def run {
    println("Hello World!!")
  }
}

trait Greeting {
  lazy val greeting: String = "Hello World!"
}