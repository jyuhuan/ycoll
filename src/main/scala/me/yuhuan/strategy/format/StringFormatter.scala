package me.yuhuan.strategy.format

/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com)
 */
trait StringFormatter[-X] {
  def str(x: X): String
}
