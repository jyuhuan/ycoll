package me.yuhuan

/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
package object collection {
  def nextPowerOfTwo(x: Int): Int = {
    var c = x - 1
    c |= c >>> 1
    c |= c >>> 2
    c |= c >>> 4
    c |= c >>> 8
    c |= c >>> 16
    c + 1
  }

  @inline def default[T]: T = {
    class Default {
      var default: T = _
    }
    (new Default).default
  }
}
