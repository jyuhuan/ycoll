package me.yuhuan.collection

import me.yuhuan.strategy.search._

/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
package object node {

  /**
   * Default search space for [[me.yuhuan.collection.node.Node Node]]s.
   */
  implicit def defaultSearchSpace4Node[T]: StateSpace[Node[T]] = new StateSpace[Node[T]] {
    override def succ(x: Node[T]): Iterable[Node[T]] = x.succ
  }
}
