package me.yuhuan.collection.nodes.binary

/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
trait BinaryNode[+A] {
  def data: A
  def left: BinaryNode[A]
  def right: BinaryNode[A]

  def isDummy: Boolean = false
  def notDummy = !isDummy
}

object BinaryNode {
  def dummy[X] = new BinaryNode[X] {
    def data: X = throw new NotImplementedError
    def left: BinaryNode[X] = throw new NotImplementedError
    def right: BinaryNode[X] = throw new NotImplementedError
    override def isDummy: Boolean = true
  }
}
