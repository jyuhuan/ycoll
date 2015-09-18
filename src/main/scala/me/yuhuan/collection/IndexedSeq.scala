package me.yuhuan.collection

/**
 * An indexed sequence.
 * Constant or near constant random access time is assumed with the `apply` method.
 *
 * @tparam X
 *
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
trait IndexedSeq[+X] extends Seq[X] {
  def apply(i: Int): X

  def head: X
  def tail: X

  // TODO: this should be implemented using Cartesian product
  // IndexedSeq(0, 1, 2, 3, 4, 5, 6, 7, 8, 9).twos = Enumerable((0, 0), (0, 1), (0, 2), ..., (9, 9))
  //  def twos: Enumerable[(X, X)]
  //  def twosUnique: Enumerable[(X, X)]
}