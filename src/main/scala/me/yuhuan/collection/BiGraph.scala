package me.yuhuan.collection

/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
trait BiGraph[K, +V, +E] extends Graph[K, V, E] {
  def incomingVertexKeysOf(k: K): Iterable[K]
  def incomingVerticesOf(i: K): Iterable[Vertex] = incomingVertexKeysOf(i).view.map(j ⇒ vertexAt(j))
  def incomingEdgeKeysOf(i: K): Iterable[(K, K)] = incomingVertexKeysOf(i).view.map(j ⇒ i → j)
  def incomingEdgesOf(i: K): Iterable[Edge] = incomingVertexKeysOf(i).view.map(j ⇒ edgeAt(i, j))
}
