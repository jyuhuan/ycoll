package me.yuhuan.strategy.search

/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
case class SearchNodeWithGValue[S](state: S, g: Double, parent: SearchNodeWithGValue[S]) extends SearchNodeLike[S, SearchNodeWithGValue[S]]
