package me.yuhuan.strategy.search

import scala.collection._

/**
 * Represents a search problem in which heuristics are defined.
 * @tparam S The type of a state in the search problem.
 */
trait StateSpaceWithCostWithHeuristic[S] {
  /**
   * Gets the heuristic value for the given state.
   * @param x The state in query.
   * @return The heuristic value.
   */
  def h(x: S): Double
  def succ(state: S): Iterable[S]
  def cost(from: S, to: S): Double


  def findPath(start: S, isGoal: S ⇒ Boolean)(implicit ss: StateSpaceWithCostWithHeuristic[S]): Seq[S] = {
    implicit object MinFOrder extends Ordering[SearchNodeWithGValueHValue[S]] {
      override def compare(x: SearchNodeWithGValueHValue[S], y: SearchNodeWithGValueHValue[S]): Int = if (y.f - x.f > 0) 1 else if (y.f - x.f < 0) -1 else 0
    }

    val fringe = mutable.PriorityQueue[SearchNodeWithGValueHValue[S]](SearchNodeWithGValueHValue(start, 0, ss.h(start), null))(MinFOrder)
    var found = false
    var goalSearchNode: SearchNodeWithGValueHValue[S] = null

    val explored = mutable.HashSet[S]()

    while (!found && fringe.nonEmpty) {

      println("Fringe size = " + fringe.size)

      val curNode = fringe.dequeue()
      val curState = curNode.state


      if (!explored.contains(curState)) {
        explored += curState
        if (isGoal(curNode.state)) {
          found = true
          goalSearchNode = curNode
        }
        else {
          val successors = ss.succ(curState).filter(s ⇒ !explored.contains(s)).map(
            nextState ⇒ SearchNodeWithGValueHValue(
              nextState,
              curNode.g + ss.cost(curState, nextState),
              ss.h(nextState),
              curNode
            ))
          successors.foreach(n ⇒ fringe enqueue n)
        }
      }
    }
    if (goalSearchNode != null) goalSearchNode.history.map(_.state) else Nil
  }



}
