package me.yuhuan.collection.factory

import me.yuhuan.collection.builder.CollectionBuilder
import me.yuhuan.collection.conversion.ScalaConversions._
/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
trait SetFactory[S[_]] {
  def newBuilder[X]: CollectionBuilder[X, S[X]]
  def apply[X](xs: X*): S[X] = {
    val b = newBuilder[X]
    b.sizeHint(xs.length)
    b addMany xs
    b.result
  }
}
