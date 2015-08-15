package me.yuhuan.collection

/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
trait BiNode[+A] extends Node[A] {
  def data: A
  def prev: Iterable[BiNode[A]]
  def succ: Iterable[BiNode[A]]
}
