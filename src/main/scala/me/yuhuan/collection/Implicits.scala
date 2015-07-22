package me.yuhuan.collection

/**
 * Created by Yuhuan Jiang (jyuhuan@gmail.com) on 7/21/15.
 */
object Implicits {
  implicit class EdgeConstruction[V, E](val x: (V, E)) extends AnyVal {
    def →(e: V) = (x._1, e, x._2)
    def ->(e: V) = this → e
  }

}
