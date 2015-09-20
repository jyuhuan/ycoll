package me.yuhuan.collection

/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
trait Map[-K, +V] extends (K ⇒ V) {
  def keys: Set[K]
  def values: Enumerable[V]
  def contains(key: K): Boolean
  def doesNotContain(key: K) = !contains(key)
  def isDefinedAt(key: K) = contains(key)

  def pairs: Enumerable[KeyValuePair[K, V]]
}
