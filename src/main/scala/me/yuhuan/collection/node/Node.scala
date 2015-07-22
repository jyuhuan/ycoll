package me.yuhuan.collection.node

/**
 * Represents a node with successors.
 * @tparam T The type of the data that this node contains.
 *
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
trait Node[+T] {
  /**
   * The data that this node contains.
   */
  def data: T

  /**
   * The successors of this node.
   */
  def succ: Iterable[Node[T]]

  /**
   * Test if this node is a leaf (i.e., has no successors)
   */
  def isLeaf = succ == Nil
}
