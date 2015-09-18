package me.yuhuan.collection.nodes

/**
 * Represents a node that has only a single parent.
 *
 * @tparam X The type of the data the node contains.
 *
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
trait PrevNode[+X] extends BasicNode[X] {
  def prev: PrevNode[X]
}

object PrevNode {
  def dummy[X] = new PrevNode[X] {
    def data: X = throw new NotImplementedError
    def prev: PrevNode[X] = throw new NotImplementedError
    override def isDummy = true
  }
}
