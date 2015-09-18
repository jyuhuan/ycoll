package me.yuhuan.collection

/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
trait Map[-A, +B] extends (A ⇒ B) {
  def keys: Set[A]
  def values: Enumerable[B]
}
