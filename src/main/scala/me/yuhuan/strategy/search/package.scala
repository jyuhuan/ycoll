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
    def pathTo(goal: S)(implicit ss: StateSpaceWithHeuristic[S]) = ss.findPath(x, _ == goal)
    def ↝(goal: S)(implicit ss: StateSpaceWithHeuristic[S]) = pathTo(goal)(ss)
    def ~~>(goal: S)(implicit ss: StateSpaceWithHeuristic[S]) = pathTo(goal)(ss)
  }

}
