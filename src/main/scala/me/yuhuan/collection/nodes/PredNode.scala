package me.yuhuan.collection.nodes

import me.yuhuan.collection.Seq

/**
 * Represents a node that has only a sequence of parent nodes.
 *
 * @tparam X The type of the data the node contains.
 *
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
trait PredNode[+X] extends BasicNode[X] {
  def pred: Seq[PredNode[X]]
}

object PredNode {
  def dummy[X] = new PredNode[X] {
    def data: X = throw new NotImplementedError
    def pred: Seq[PredNode[X]] = throw new NotImplementedError
    override def isDummy = true
  }
}
