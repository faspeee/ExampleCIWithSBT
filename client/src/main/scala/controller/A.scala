package controller

import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration
import scala.language.postfixOps
import scala.concurrent.ExecutionContext.Implicits.global
abstract class A {
  var a = 0
  readData().onComplete(t=>a=t.get)
  def readData():Future[Int]= {
    Thread.sleep(5000)
    val r:Future[Int]=Future(2)
    r
  }
}

class B extends A{
  val b=1
  def sum(): Int ={
    a+b
  }
}

object tryB extends App{
  val s = new B
  print(s.sum())
}