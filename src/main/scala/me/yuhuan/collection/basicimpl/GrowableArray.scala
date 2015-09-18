package me.yuhuan.collection.basicimpl

import me.yuhuan.collection._

import scala.reflect.ClassTag

/**
 * Basic implementation of an data and structure mutable array.
 * @param lengthNeeded
 * @param ct
 * @tparam X
 *
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
class GrowableArray[X](lengthNeeded: Int)(implicit ct: ClassTag[X]) extends Enumerable[X] {
  var data = Array.ofDim[X](math.max(nextPowerOfTwo(lengthNeeded), Config.ArrayInitSize))
  var length = lengthNeeded

  def newEnumerator: Enumerator[X] = new Enumerator[X] {
    var i = -1
    def moveNext(): Boolean = {
      i += 1
      i < data.length
    }
    def current: X = data(i)
  }

  def apply(i: Int): X = data(i)
  def tail: X = data(length - 1)
  def head: X = data(0)

  def growIfNeeded(lengthNeeded: Int): Unit = {
    if (lengthNeeded > data.length) {
      val newData = Array.ofDim[X](nextPowerOfTwo(lengthNeeded))
      Array.copy(data, 0, newData, 0, data.length)
      data = newData
    }
  }

  def append(x: X): Unit = {
    this.length += 1
    growIfNeeded(this.length)
    this.data(this.length - 1) = x
  }
}

object GrowableArray {
  def apply[X](xs: X*)(implicit ct: ClassTag[X]) = {
    val result = new GrowableArray[X](xs.length)
    result.data = xs.toArray.clone()
    result
  }

}
