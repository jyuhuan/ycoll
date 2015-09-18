package me.yuhuan.collection.nodes

import me.yuhuan.collection.Seq

/**
 * Represents a node that has multiple parents and one child.
 *
 * @tparam X The type of the data the node contains.
 *
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
trait PredNextNode[+X] extends PredNode[X] with NextNode[X] {
  def pred: Seq[PredNextNode[X]]
  def next: PredNextNode[X]
  override def isDummy = false
}

object PredNextNode {
  def apply[X] = new PredNextNode[X] {
    def pred: Seq[PredNextNode[X]] = throw new NotImplementedError
    def next: PredNextNode[X] = throw new NotImplementedError
    def data: X = throw new NotImplementedError
    override def isDummy = true
  }
}