package me.yuhuan.collection.graph

/**
 * Represents a data-mutable directed graph.
 *
 * @tparam I The type of the index of an vertex.
 * @tparam V The type of the data in an vertex.
 * @tparam E The type of the data of an edge.
 *
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
trait DMutableGraph[I, V, E] extends Graph[I, V, E] {
  /**
   * Updates the data of the vertex at the given index.
   * @param i The index of the vertex to be updated.
   * @param v The new data for the vertex.
   */
  def update(i: I, v: V)

  /**
   * Updates the data of the edge at the give pair of indices.
   * @param i The first index of the edge.
   * @param j The second index of the edge.
   * @param e The new data for the edge.
   */
  def update(i: I, j: I, e: E)
}
