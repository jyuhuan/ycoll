package me.yuhuan.collection.builder

import me.yuhuan.collection.Enumerable

/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
trait CollectionBuilder[-T, +C] {
  def add(x: T): Unit
  def addMany(xs: Enumerable[T]) = xs.foreach(add)
  def result: C
  def sizeHint: Int
}
