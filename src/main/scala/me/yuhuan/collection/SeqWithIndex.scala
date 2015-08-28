package me.yuhuan.collection

/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
trait SeqWithIndex[+X] extends Enumerable[X] {
  def apply(i: Int): X
}