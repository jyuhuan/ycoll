package me.yuhuan.collection

/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
trait SingleParentNode[+A] {
  def data: A
  def parent: A
  def children: Iterable[A]
}
