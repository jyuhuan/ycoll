package me.yuhuan.strategy

import me.yuhuan.collection.node.Node

/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
package object search {
  /**
   * Everything has the pathTo method for searching.
   */
  implicit class EverythingIsSearchable[S](val x: S) extends AnyVal {
    def pathTo(goal: S)(implicit ss: StateSpaceWithHeuristic[S]) = SearchNode[S](x, 0, 0, 0, null).pathTo(goal)(ss)
    def ↝(goal: S)(implicit ss: StateSpaceWithHeuristic[S]) = pathTo(goal)(ss)
    def ~~>(goal: S)(implicit ss: StateSpaceWithHeuristic[S]) = pathTo(goal)(ss)
  }

  /**
   * Default search space for [[me.yuhuan.collection.node.Node Node]]s.
   */
  implicit def defaultSearchSpace4Node[T]: StateSpaceWithHeuristic[Node[T]] = new StateSpaceWithHeuristic[Node[T]] {
    override def succ(x: Node[T]): Iterable[Node[T]] = x.succ
    override def cost(from: Node[T], to: Node[T]): Double = 1
    override def h(x: Node[T]): Double = 0
  }
}
