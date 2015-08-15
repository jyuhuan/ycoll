package me.yuhuan.collection

/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
trait Node[+A] {
  def data: A
  def succ: Iterable[Node[A]]
}
