package me.yuhuan.collection.mutable

import me.yuhuan.collection._
import me.yuhuan.collection.builder.CollectionBuilder
import me.yuhuan.collection.conversion.JavaConversions._
import me.yuhuan.collection.factory.MapFactory

/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
class HashMap[K, V](data: java.util.HashMap[K, V]) extends DataMutableMap[K, V] { outer ⇒
  def update(k: K, v: V): Unit = data.put(k, v)

  def keys: Set[K] = new Set[K] { inner ⇒
    def contains(x: K): Boolean = outer.data.containsKey(x)
    def elements: Enumerable[K] = new Enumerable[K] {
      def newEnumerator: Enumerator[K] = outer.data.keySet().iterator()
    }
  }

  def values: Enumerable[V] = new Enumerable[V] { inner ⇒
    def newEnumerator: Enumerator[V] = outer.data.values().iterator()
  }

  def contains(key: K): Boolean = data containsKey key

  def apply(k: K): V = data get k

  def tryApply(k: K): Option[V] = {
    if (this doesNotContain k) None
    else Some(this(k))
  }

  def ?(k: K): Option[V] = this tryApply k

  def pairs: Enumerable[KeyValuePair[K, V]] = new Enumerable[KeyValuePair[K, V]] {
    def newEnumerator: Enumerator[KeyValuePair[K, V]] = {
      outer.data.entrySet().iterator().map(x ⇒ new KeyValuePair[K, V](x.getKey, x.getValue))
    }
  }
}

object HashMap extends MapFactory[HashMap] {
  def newBuilder[K, V]: CollectionBuilder[(K, V), HashMap[K, V]] = new CollectionBuilder[(K, V), HashMap[K, V]] {
    val data = new java.util.HashMap[K, V]()

    def sizeHint(size: Int): Unit = { } //TODO: This is reserved for my own implementation in the future. Now HashMap is simply java.util.HashMap.

    def result: HashMap[K, V] = new HashMap[K, V](data)

    def add(x: (K, V)): Unit = {
      data.put(x._1, x._2)
      val bp = 0
    }
  }
}
