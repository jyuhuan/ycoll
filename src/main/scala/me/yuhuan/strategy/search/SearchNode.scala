package me.yuhuan.strategy.search

import scala.collection._



/**
 * A node in the fringe of a search runtime.
 * Other names of this class in the literature are: frontier, paths, etc.
 *
 * @param parent The parent search node, i.e., the node that generated this node.
 * @tparam S The type of a state in this search problem.
 */
case class SearchNode[S](state: S, parent: SearchNode[S]) {
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




