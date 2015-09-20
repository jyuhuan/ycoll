package me.yuhuan.collection

/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
trait DataMutableMap[K, V] extends Map[K, V] {
  def update(k: K, v: V): Unit
}
