package me.yuhuan.collection

/**
 * Represents a data-mutable directed graph.
 *
 * @tparam K The type of the index of an vertex.
 * @tparam N The type of the data in an vertex.
 * @tparam E The type of the data of an edge.
 *
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
trait DataMutableGraph[K, N, E] extends Graph[K, N, E] {
  /**
   * Updates the data of the vertex at the given index.
   * @param k The index of the vertex to be updated.
   * @param n The new data for the vertex.
   */
  def update(k: K, n: N)

  /**
   * Updates the data of the edge at the give pair of indices.
   * @param k The first index of the edge.
   * @param j The second index of the edge.
   * @param e The new data for the edge.
   */
  def update(k: K, j: K, e: E)
}
