package me.yuhuan.collection.nodes

/**
 * Represents a node that has single parent node and single child node.
 *
 * @tparam X The type of the data the node contains.
 *
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
trait PrevNextNode[+X] extends PrevNode[X] with NextNode[X] {
  def prev: PrevNextNode[X]
  def next: PrevNextNode[X]
}

object PrevNextNode {
  def dummy[X] = new PrevNextNode[X] {
    def prev: PrevNextNode[X] = throw new NotImplementedError
    def next: PrevNextNode[X] = throw new NotImplementedError
    def data: X = throw new NotImplementedError
    override def isDummy = true
  }
}
