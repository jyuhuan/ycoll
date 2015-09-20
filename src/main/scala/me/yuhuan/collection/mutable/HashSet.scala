package me.yuhuan.collection.mutable

import me.yuhuan.collection.builder.CollectionBuilder
import me.yuhuan.collection.conversion.JavaConversions
import me.yuhuan.collection.factory.SetFactory
import me.yuhuan.collection.{Enumerator, Enumerable, Set}

/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
class HashSet[X](data: java.util.HashSet[X]) extends Set[X] { outer ⇒
  def contains(x: X): Boolean = data contains x

  def elements: Enumerable[X] = new Enumerable[X] {
    def newEnumerator: Enumerator[X] = JavaConversions.javaIteratorAsYEnumerator(outer.data.iterator())
  }
}

object HashSet extends SetFactory[HashSet] {
  def newBuilder[X]: CollectionBuilder[X, HashSet[X]] = new CollectionBuilder[X, HashSet[X]] {
    val data = new java.util.HashSet[X]()

    def sizeHint(size: Int): Unit = { }//TODO: This is reserved for my own implementation in the future. Now HashMap is simply java.util.HashSet.

    def result: HashSet[X] = new HashSet[X](data)

    def add(x: X): Unit = data.add(x)
  }
}