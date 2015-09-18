package me.yuhuan.collection.factory

import me.yuhuan.collection.builder.CollectionBuilder

import scala.language.higherKinds

/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
trait CollectionFactory[C[_]] {

  def newBuilder[T]: CollectionBuilder[T, C[T]]

  def fill[T](size: Int)(value: T): C[T] = {
    val b = newBuilder[T]
    for (i ← 0 until size) b add value
    b.result
  }

  def tabulate[T](size: Int)(f: Int ⇒ T): C[T] = {
    val b = newBuilder[T]
    for (i ← 0 until size) b add f(i)
    b.result
  }
}
