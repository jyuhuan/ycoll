package me.yuhuan.strategy.search

/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
case class SearchNodeWithGValueHValue[S](state: S, g: Double, h: Double, parent: SearchNodeWithGValueHValue[S]) extends SearchNodeLike[S, SearchNodeWithGValueHValue[S]] {
  def f = g + h
}
