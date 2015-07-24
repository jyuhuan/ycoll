package me.yuhuan.strategy.search

import scala.collection._

/**
 * A node in the fringe of a search runtime.
 * Other names of this class in the literature are: frontier, paths, etc.
 *
 * @param goal The goal state.
 * @param g The path cost so far. This equals to the sum of all cost on the path so far.
 * @param h The heuristic value for
 * @param depth The depth of the search node. This equals to the length of the path so far.
 * @param parent The parent search node, i.e., the node that generated this node.
 * @tparam S The type of a state in this search problem.
 */
case class SearchNode[S](state: S, g: Double, h: Double, depth: Int, parent: SearchNode[S]) {
  def f = g + h
  def history: Seq[SearchNode[S]] = {
   val result = mutable.ListBuffer[SearchNode[S]]()
   var cur = this
   while (cur != null) {
     result prepend cur
     cur = cur.parent
   }
   result
  }
}

