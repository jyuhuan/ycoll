package me.yuhuan.collection.nodes

import me.yuhuan.collection.Seq

/**
 * Represents a node that has a single parent node and multiple child nodes.
 *
 * @tparam A
 *
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
trait PrevSuccNode[+A] extends PrevNode[A] with SuccNode[A] {
  def prev: PrevSuccNode[A]
  def succ: Seq[PrevSuccNode[A]]
}

object PrevSuccNode {
  def dummy[A] = new PrevSuccNode[A] {
    override def prev: PrevSuccNode[A] = throw new NotImplementedError
    override def succ: Seq[PrevSuccNode[A]] = throw new NotImplementedError
    override def data: A = throw new NotImplementedError
    override def isDummy: Boolean = true
  }
}
