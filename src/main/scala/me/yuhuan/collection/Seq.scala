package me.yuhuan.collection

import me.yuhuan.algebra.Equality

/**
 * Represents a sequence.
 * A sequence is different from `Enumerable`s in that elements in a sequence has an index,
 * while there isn't such element order defined in an `Enumerable`.
 *
 * @tparam A Type of elements in the sequence.
 *
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
trait Seq[+A] /*extends PartialFunction[A]*/ {

  def apply(idx: Int): A

  def indexWhere(p: A ⇒ Boolean): Int

  def indexOf[B >: A](item: B)(implicit ev: Equality[B]): Int = indexWhere(x ⇒ ev.eq(x, item))

  def lastIndexWhere(p: A ⇒ Boolean): Int

  def lastIndexOf[B >: A](item: B)(implicit ev: Equality[B]): Int = lastIndexWhere(x ⇒ ev.eq(x, item))

  def length: Int

  def permutations: Enumerable[A]

  def combinations: Enumerable[A]

  def reversed: Seq[A]

}
