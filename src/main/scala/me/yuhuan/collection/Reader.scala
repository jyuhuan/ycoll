package me.yuhuan.collection


/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
trait Reader[+X] {
  def read: Option[X]
}

object Reader {
  def empty: Reader[Nothing] = new Reader[Nothing] {
    def read: Option[Nothing] = None
  }
}
