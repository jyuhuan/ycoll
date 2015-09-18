package me.yuhuan.collection.nodes

/**
 * A node for all other types of nodes to derive from.
 * It is useless to try to instantiate this trait.
 * It is only meaningful to instantiate sub-traits of this trait.
 *
 * @tparam X The type of the data the node contains.
 *
 * @author Yuhuan Jiang (jyuhuan@gmail.com)
 */
trait BasicNode[+X] {
  def data: X

  def isDummy: Boolean = false
  def notDummy = !isDummy
}
