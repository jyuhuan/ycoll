package me.yuhuan.collection

/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
trait BiGraph[K, +N, +E] extends Graph[K, N, E] {
  def incomingNodeKeysOf(k: K): Iterable[K]
  def incomingNodesOf(k: K): Iterable[Node] = incomingNodeKeysOf(k).view.map(j ⇒ nodeAt(j))
  def incomingEdgeKeysOf(k: K): Iterable[(K, K)] = incomingNodeKeysOf(k).view.map(j ⇒ k → j)
  def incomingEdgesOf(k: K): Iterable[Edge] = incomingNodeKeysOf(k).view.map(j ⇒ edgeAt(k, j))
}
