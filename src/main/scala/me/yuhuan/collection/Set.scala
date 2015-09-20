package me.yuhuan.collection

import scala.annotation.unchecked.{uncheckedVariance => uv}
/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
trait Set[X] extends (X ⇒ Boolean) {

  def contains(x: X): Boolean
  def doesNotContain(x: X) = !contains(x)

  def apply(x: X) = this contains x

  def isSubsetOf[Y <: X](that: Set[Y]): Boolean = ???
  def ⊆[Y <: X](that: Set[Y]) = this isSubsetOf that

  def isProperSubsetOf[Y <: X](that: Set[Y]): Boolean = ???
  def ⊂[Y <: X](that: Set[Y]) = this isProperSubsetOf that

  def union[Y <: X](that: Set[Y]): Set[X] = ???
  def ∪[Y <: X](that: Set[Y]): Set[X] = this union that

  def intersect[Y <: X](that: Set[Y]): Set[X] = ???
  def ∩[Y <: X](that: Set[Y]): Set[X] = this intersect that

  def complement[Y <: X](that: Set[Y]): Set[X] = ???
  def \[Y <: X](that: Set[Y]): Set[X] = this complement that

  def elements: Enumerable[X]
}
