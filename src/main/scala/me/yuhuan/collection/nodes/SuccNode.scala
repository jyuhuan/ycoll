package me.yuhuan.collection.nodes

import me.yuhuan.collection.Seq

/**
 * Represents a node that has only multiple parent nodes.
 *
 * @tparam X The type of the data the node contains.
 *
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
trait SuccNode[+X] extends BasicNode[X] {
  def succ: Seq[SuccNode[X]]
}

object SuccNode {
  def dummy[X]: SuccNode[X] = new SuccNode[X] {
    override def data: X = throw new NotImplementedError
    override def succ: Seq[SuccNode[X]] = throw new NotImplementedError
    override def isDummy: Boolean = true
  }
}