package me.yuhuan.collection.graph

/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
trait GraphFactory[G[_, _, _]] {
  def newBuilder[I, V, E]: GraphBuilder[I, V, E]

  def apply[I, V, E](vertices: (I, V)*)(edges: (I, I, E)*): Graph[I, V, E] = {
    val b = newBuilder[I, V, E]
    b.addVertices(vertices: _*)
    b.addEdges(edges: _*)
    b.result
  }
}
