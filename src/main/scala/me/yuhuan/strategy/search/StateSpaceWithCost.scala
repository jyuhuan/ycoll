package me.yuhuan.strategy.search

import scala.collection._

/**
 * Represents a search problem.
 * @tparam S The type of a state in the search problem.
 */
trait StateSpaceWithCost[S] extends StateSpace[S] {
  /**
   * Gets the cost from a state to another state.
   * @param from From state.
   * @param to To state.
   * @return The cost.
   */
  def cost(from: S, to: S): Double
  //def findPathWithCost(start: S, isGoal: S ⇒ Boolean)(implicit ss: StateSpaceWithCost[S]): Seq[S] = ???
}
