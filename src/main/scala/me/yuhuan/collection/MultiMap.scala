package me.yuhuan.collection

/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
trait MultiMap[-K, V] {
  def apply(k: K): Set[V]
}
