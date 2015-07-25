package me.yuhuan.strategy.search

import scala.collection.mutable

/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
case class SearchNodeWithGValueHValue[S](state: S, g: Double, h: Double, parent: SearchNodeWithGValueHValue[S]) {
  def f = g + h
  def history: Seq[SearchNodeWithGValueHValue[S]] = {
    val result = mutable.ListBuffer[SearchNodeWithGValueHValue[S]]()
    var cur = this
    while (cur != null) {
      result prepend cur
      cur = cur.parent
    }
    result
  }
}
