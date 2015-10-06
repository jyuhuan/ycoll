package me.yuhuan.collection.mutable


import me.yuhuan.collection.{Enumerable, Seq, IndexedSeq}

/**
 * An alternative to plain `String`s.
 * Created by Yuhuan Jiang (jyuhuan@gmail.com) on 10/4/15.
 */
class Rope extends IndexedSeq[Char] {
  def apply(i: Int): Char = ???

  def tail: Char = ???

  def head: Char = ???

  def combinations: Enumerable[Char] = ???

  def lastIndexWhere(p: (Char) => Boolean): Int = ???

  def length: Int = ???

  def indexWhere(p: (Char) => Boolean): Int = ???

  def permutations: Enumerable[Char] = ???

  def reversed: Seq[Char] = ???
}
