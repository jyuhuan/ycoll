package me.yuhuan.collection.basicimpl

import me.yuhuan.collection.{Enumerator, Enumerable}
import me.yuhuan.collection.nodes.PrevNextNode

/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
class LinkedArray[X] extends Enumerable[X] { outer =>

  def newEnumerator: Enumerator[X] = new Enumerator[X] {
    var cur = outer.dummy
    def moveNext(): Boolean = {
      cur = cur.next
      cur.notDummy
    }
    def current: X = cur.data
  }

  class Node[X](var data: X, var prev: Node[X], var next: Node[X]) extends PrevNextNode[X]

  val dummy: Node[X] = new Node[X](data = default[X], prev = null, next = null) {
    override def isDummy = true
  }
  dummy.next = dummy
  dummy.prev = dummy

  def head: Node[X] = dummy.next

  def tail: Node[X] = dummy.prev

  def append(x: X): Unit = {
    val newNode = new Node[X](data = x, prev = tail, next = dummy)

    tail.next = newNode

    dummy.prev = newNode


    val bp = 0
  }

}
