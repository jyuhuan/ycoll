package me.yuhuan.collection.nodes

/**
 * Represents a node that has only one child node.
 *0
 * @tparam X The type of the data the node contains.
 *
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
trait NextNode[+X] extends BasicNode[X] {
  def next: NextNode[X]
}

object NextNode {
  def apply[X] = new NextNode[X] {
    def data: X = throw new NotImplementedError
    def next: NextNode[X] = throw new NotImplementedError
    override def isDummy = true
  }
}
