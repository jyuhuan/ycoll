package me.yuhuan.collection.factory

import me.yuhuan.collection.builder.{CollectionBuilder, Builder}
import me.yuhuan.collection.conversion.ScalaConversions._


/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
trait MapFactory[M[_,_]] {
  def newBuilder[K, V]: CollectionBuilder[(K, V), M[K, V]]

  def apply[K, V](pairs: (K, V)*): M[K, V] = {
    val b = newBuilder[K, V]
    b.sizeHint(pairs.length)
    b addMany pairs
    b.result
  }
}
