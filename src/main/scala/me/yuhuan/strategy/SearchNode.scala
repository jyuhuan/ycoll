package me.yuhuan.strategy

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
case class SearchNode[S](goal: S, g: Double, h: Double, depth: Int, parent: SearchNode[S]) {
   def f = g + h

   def pathTo(goalState: S)(ss: StateSpaceWithHeuristic[S]): Seq[S] = {
     val fringe = mutable.PriorityQueue[SearchNode[S]](this)(Ordering.by(_.h))
     var found = false
     var goalSearchNode: SearchNode[S] = null
     while (!found && fringe.nonEmpty) {
       val cur = fringe.dequeue()
       val curState = cur.goal
       if (curState == goalState) {
         found = true
         goalSearchNode = cur
       }
       else {
         val successors = ss.succ(curState).map(
           nextState ⇒ SearchNode(
             nextState,
             cur.g + ss.cost(curState, nextState),
             ss.h(nextState),
             cur.depth + 1,
             cur
           ))
         successors.foreach(n ⇒ fringe enqueue n)
       }
     }
     if (goalSearchNode != null) goalSearchNode.history.map(_.goal) else Nil
   }

   def ↝(state: S)(ss: StateSpaceWithHeuristic[S]) = pathTo(state)(ss)
   def ~~>(state: S)(ss: StateSpaceWithHeuristic[S]) = pathTo(state)(ss)

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

