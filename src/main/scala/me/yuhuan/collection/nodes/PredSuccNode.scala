package me.yuhuan.collection.nodes

import me.yuhuan.collection.Seq

import me.yuhuan.collection.Seq

/**
 * Represents a node that has multiple parent nodes and multiple child nodes.
 *
 * @tparam A
 *
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
trait PredSuccNode[+A] extends PredNode[A] with SuccNode[A] {
  def pred: Seq[PredSuccNode[A]]
  def succ: Seq[PredSuccNode[A]]
}

object PredSuccNode {
  def dummy[X] = new PredSuccNode[X] {
    def pred: Seq[PredSuccNode[X]] = throw new NotImplementedError
    def succ: Seq[PredSuccNode[X]] = throw new NotImplementedError
    def data: X = throw new NotImplementedError
    override def isDummy = true
  }
}