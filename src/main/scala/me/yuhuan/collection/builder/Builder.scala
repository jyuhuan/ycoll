package me.yuhuan.collection.builder

import me.yuhuan.collection.HasForeach

/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
trait Builder[-X, +C] {
  def add(x: X): Unit
  def +=(x: X) = this add x

  def addMany(xs: HasForeach[X]) = xs foreach add
  def ++=(xs: HasForeach[X]) = this addMany xs

  def result: C
}
