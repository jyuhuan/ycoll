package me.yuhuan.collection

/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
class KeyValuePair[-K, +V](val key: K, val value: V) {
  override def toString = s"($key, $value)"
}
