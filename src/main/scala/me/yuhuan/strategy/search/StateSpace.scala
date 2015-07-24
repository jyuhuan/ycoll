package me.yuhuan.strategy.search

import scala.collection._

/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
trait StateSpace[S] {

  /**
   * Gets the successor states of the given state.
   * @param state The state to be queried.
   * @return Successor states.
   */
  def succ(state: S): Iterable[S]

  def findPath(start: S, isGoal: S ⇒ Boolean)(implicit ss: StateSpace[S]): Seq[S] = {
    val fringe = mutable.Queue[SearchNode[S]](SearchNode(start, null))//(Ordering.by(_.f))
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
            curNode
          ))
        successors.foreach(n ⇒ fringe enqueue n)
      }
    }
    if (goalSearchNode != null) goalSearchNode.history.map(_.state) else Nil
  }

}
