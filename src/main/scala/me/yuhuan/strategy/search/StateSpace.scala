package me.yuhuan.strategy.search

import scala.collection._

/**
 * Represents a search problem.
 * @tparam S The type of a state in the search problem.
 */
trait StateSpace[S] {
  /**
   * Gets the successor states of the given state.
   * @param state The state to be queried.
   * @return Successor states.
   */
  def succ(state: S): Iterable[S]

  /**
   * Gets the cost from a state to another state.
   * @param from From state.
   * @param to To state.
   * @return The cost.
   */
  def cost(from: S, to: S): Double

  def findPath(start: S, isGoal: S ⇒ Boolean)(implicit ss: StateSpaceWithHeuristic[S]): Seq[S] = {
    val fringe = mutable.PriorityQueue[SearchNode[S]](SearchNode(start, 0, 0, 0, null))(Ordering.by(_.f))
    var found = false
    var goalSearchNode: SearchNode[S] = null

    val explored = mutable.HashSet[S]()

    while (!found && fringe.nonEmpty) {
      val curNode = fringe.dequeue()
      val curState = curNode.state
      explored += curState

      if (isGoal(curNode.state)) {
        found = true
        goalSearchNode = curNode
      }
      else {
        val successors = ss.succ(curState).filter(s ⇒ !explored.contains(s)).map(
          nextState ⇒ SearchNode(
            nextState,
            curNode.g + ss.cost(curState, nextState),
            ss.h(nextState),
            curNode.depth + 1,
            curNode
          ))
        successors.foreach(n ⇒ fringe enqueue n)
      }
    }
    if (goalSearchNode != null) goalSearchNode.history.map(_.state) else Nil
  }
}
