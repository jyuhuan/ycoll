package me.yuhuan.collection.graph

/**
 * Represents a structure-mutable directed graph.
 *
 * @tparam I The type of the index of an vertex.
 * @tparam V The type of the data in an vertex.
 * @tparam E The type of the data of an edge.
 *
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
trait SMutableGraph[I, V, E] extends DMutableGraph[I, V, E] {
  /**
   * Adds a vertex.
   * @param i The index of the new vertex.
   * @param v The data of the new vertex.
   */
  def addVertex(i: I, v: V)
  def +=(v: (I, V)): Unit = addVertex(v._1, v._2)


  /**
   * Removes the vertex at the give index.
   * @param i The index of the vertex to be deleted.
   */
  def removeVertexAt(i: I)

  /**
   * Adds an edge.
   * @param i The first index of the new edge.
   * @param j The second index of the new edge.
   * @param edge The data of the enw edge.
   */
  def addEdge(i: I, j: I, edge: E)
  def +=(e: (I, I, E)): Unit = addEdge(e._1, e._2, e._3)

  /**
   * Removes the edge at the given index.
   * @param i The first index of the edge to be deleted.
   * @param j The second index of the edge to be deleted.
   */
  def removeEdgeAt(i: I, j: I)
}
