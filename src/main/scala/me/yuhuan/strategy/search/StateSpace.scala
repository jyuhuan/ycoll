package me.yuhuan.strategy.search

import scala.annotation.unchecked.{uncheckedVariance ⇒ uv}
import scala.collection._

/**
 * Represents a search problem.
 * @tparam S The type of a state in the search problem.
 */
trait StateSpace[-S] {
  /**
   * Gets the successor states of the given state.
   * @param state The state to be queried.
   * @return Successor states.
   */
  def succ(state: S): Iterable[S @uv]

  /**
   * Gets the cost from a state to another state.
   * @param from From state.
   * @param to To state.
   * @return The cost.
   */
  def cost(from: S, to: S): Double
}
