package me.yuhuan.collection.builder

import me.yuhuan.collection.HasForeach

/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
trait CollectionBuilder[-T, +C] {
  def add(x: T): Unit
  def addMany(xs: HasForeach[T]) = xs.foreach(add)
  def result: C
  def sizeHint: Int
  def +(x: T): Unit
  def ++(xs: HasForeach[T]) = xs.foreach(add)
}
