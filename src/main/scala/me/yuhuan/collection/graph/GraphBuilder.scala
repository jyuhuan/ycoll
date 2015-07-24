package me.yuhuan.collection.graph

/**
 * A Builder for any graph.
 *
 * @tparam I The type of the index of an vertex.
 * @tparam V The type of the data in an vertex.
 * @tparam E The type of the data of an edge.
 *
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
trait GraphBuilder[-I, -V, -E, +G] {
  /**
   * Adds a vertex to the graph.
   * @param i The index of the vertex.
   * @param v The data of the vertex.
   */
  def addVertex(i: I, v: V)

  /**
   * Adds an edge to the graph.
   * @param i The first index of the edge.
   * @param j The second index of the edge.
   * @param e The data of the edge.
   */
  def addEdge(i: I, j: I, e: E)

  def +=(v: (I, V)) = addVertex(v._1, v._2)
  def +=(e: (I, I, E)) = addEdge(e._1, e._2, e._3)

  /**
   * Adds multiple vertices at once to the graph.
   * @param vs The pairs of each vertex's index and data.
   */
  def addVertices(vs: (I, V)*) = vs foreach +=

  /**
   * Adds multiple edges at once to the graph.
   * @param es The triple of each edge's first and second indices and data.
   */
  def addEdges(es: (I, I, E)*) = es foreach +=

  /**
   * Returns the graph built from this builder.
   * @return A graph containing the vertices and edges added (if any).
   */
  def result: G
}
