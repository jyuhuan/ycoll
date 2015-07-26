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
    def simplePathTo(goal: S)(implicit ss: StateSpace[S]) = ss.findPath(x, _ == goal)(ss)
    def costlyPathTo(goal: S)(implicit ss: StateSpaceWithCost[S]) = ss.findPath(x, _ == goal)(ss)
    def heuristicPathTo(goal: S)(implicit ss: StateSpaceWithCostWithHeuristic[S]) = ss.findPath(x, _ == goal)(ss)

//    def ↝(goal: S)(implicit ss: StateSpace[S]) = pathTo(goal)(ss)
    def -->(goal: S)(implicit ss: StateSpace[S]) = simplePathTo(goal)(ss)
    def ==>(goal: S)(implicit ss: StateSpaceWithCost[S]) = costlyPathTo(goal)(ss)
    def ~~>(goal: S)(implicit ss: StateSpaceWithCostWithHeuristic[S]) = heuristicPathTo(goal)(ss)
  }

}
