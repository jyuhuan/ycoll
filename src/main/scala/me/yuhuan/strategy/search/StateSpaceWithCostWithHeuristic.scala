package me.yuhuan.strategy.search

/**
 * Represents a search problem in which heuristics are defined.
 * @tparam S The type of a state in the search problem.
 */
trait StateSpaceWithCostWithHeuristic[S] extends StateSpaceWithCost[S] {
  /**
   * Gets the heuristic value for the given state.
   * @param x The state in query.
   * @return The heuristic value.
   */
  def h(x: S): Double
}
