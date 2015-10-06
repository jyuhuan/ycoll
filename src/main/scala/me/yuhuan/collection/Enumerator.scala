package me.yuhuan.collection


/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
trait Enumerator[+X] {
  def next: Option[X]
}

object Enumerator {
  def empty: Enumerator[Nothing] = new Enumerator[Nothing] {
    def next: Option[Nothing] = None
  }
}
