package me.yuhuan.collection

/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
trait HasForeach[+X] {
  def foreach[Y](f: (X ⇒ Y)): Unit
}
