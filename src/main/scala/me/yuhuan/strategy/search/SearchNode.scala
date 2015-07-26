package me.yuhuan.strategy.search

/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
case class SearchNode[S](state: S, parent: SearchNode[S]) extends SearchNodeLike[S, SearchNode[S]]