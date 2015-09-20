package me.yuhuan.collection.nodes.binary

/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
trait ParentedBinaryNode[+A] extends BinaryNode[A] {
  def parent: ParentedBinaryNode[A]
}

object ParentedBinaryNode {
  def dummy[A] = new ParentedBinaryNode[A] {
    def parent: ParentedBinaryNode[A] = throw new NotImplementedError
    def data: A = throw new NotImplementedError
    def left: BinaryNode[A] = throw new NotImplementedError
    def right: BinaryNode[A] = throw new NotImplementedError
    override def isDummy: Boolean = true
  }
}