package me.yuhuan.collection.graph

import scala.language.higherKinds

/**
 * Contains common constructors for any graph.
 *
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 * @tparam G The kind of the graph.
 */
trait GraphFactory[G[_, _, _]] {
  /**
   * Returns a new builder for this kind of graph.
   *
   * @tparam I The type of the index of an vertex.
   * @tparam V The type of the data in an vertex.
   * @tparam E The type of the data of an edge.
   *
   * @return A new builder for this kind of graph.
   */
  implicit def newBuilder[I, V, E]: GraphBuilder[I, V, E, G[I, V, E]]

  /**
   * Creates a graph by specifying the vertices and edges.
   *
   * @param vertices The vertices of the graph.
   * @param edges The edges of the graph.
   * @tparam I The type of the index of an vertex.
   * @tparam V The type of the data in an vertex.
   * @tparam E The type of the data of an edge.
   *
   * @return The graph which contains the specified vertices and edges.
   */
  def apply[I, V, E](vertices: (I, V)*)(edges: (I, I, E)*): G[I, V, E] = {
    val b = newBuilder[I, V, E]
    b.addVertices(vertices: _*)
    b.addEdges(edges: _*)
    b.result
  }
}
