package game

import scala.collection.mutable.Stack

extension [A](stk: Stack[A]) def pop(n: Int): List[A] =
  val res = stk.take(n).toList
  stk.dropInPlace(n)
  res
