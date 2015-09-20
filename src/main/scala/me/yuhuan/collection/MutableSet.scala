package me.yuhuan.collection

/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
trait MutableSet[X] extends Set[X] {
  def add(x: X): Unit
  def +=(x: X) = this add x
  def remove(x: X): Unit
  def -=(x: X) = this remove x
}
