package me.yuhuan.collection

/**
 * Represents a structure-mutable directed graph.
 *
 * @tparam K The type of the index of an vertex.
 * @tparam N The type of the data in an vertex.
 * @tparam E The type of the data of an edge.
 *
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
trait StructureMutableGraph[K, N, E] extends DataMutableGraph[K, N, E] {
  /**
   * Adds a vertex.
   * @param i The index of the new vertex.
   * @param v The data of the new vertex.
   */
  def addNode(i: K, v: N)
  def +=(v: (K, N)): Unit = addNode(v._1, v._2)


  /**
   * Removes the vertex at the give index.
   * @param i The index of the vertex to be deleted.
   */
  def removeVertexAt(i: K)

  /**
   * Adds an edge.
   * @param i The first index of the new edge.
   * @param j The second index of the new edge.
   * @param e The data of the enw edge.
   */
  def addEdge(i: K, j: K, e: E)
  def +=(e: (K, K, E)): Unit = addEdge(e._1, e._2, e._3)

  /**
   * Removes the edge at the given index.
   * @param i The first index of the edge to be deleted.
   * @param j The second index of the edge to be deleted.
   */
  def removeEdgeAt(i: K, j: K)
}
