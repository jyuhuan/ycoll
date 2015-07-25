package me.yuhuan.strategy.search

import scala.collection.mutable

/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
case class SearchNodeWithGValue[S](state: S, g: Double, parent: SearchNodeWithGValue[S]) {
  def history: Seq[SearchNodeWithGValue[S]] = {
    val result = mutable.ListBuffer[SearchNodeWithGValue[S]]()
    var cur = this
    while (cur != null) {
      result prepend cur
      cur = cur.parent
    }
    result
  }
}
