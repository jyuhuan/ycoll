package me.yuhuan.collection.builder

import me.yuhuan.collection.HasForeach

/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
trait CollectionBuilder[-X, +C] extends Builder[X, C] {
  def sizeHint(size: Int): Unit
}
