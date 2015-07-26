package me.yuhuan.strategy.search

import scala.collection._

/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
trait SearchNodeLike[+S, +Node <: SearchNodeLike[S, Node]] { self: Node ⇒

  def parent: Node
  def state: S

  def history: Seq[Node] = {
    val result = mutable.ListBuffer[Node]()
    var cur = self
    while (cur != null) {
      result prepend cur
      cur = cur.parent
    }
    result
  }

}







