package me.yuhuan.collection.graph

import me.yuhuan.collection.node.Node
import me.yuhuan.strategy.format.StringFormatter

import scala.collection.Set


/**
 * Represents a directed graph.
 *
 * @tparam I The type of the index of an vertex.
 * @tparam V The type of the data in an vertex.
 * @tparam E The type of the label of an edge.
 *
 * @author Tongfei Chen (ctongfei@gmail.com).
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
trait Graph[@specialized(Int) I, +V, +E] { outer ⇒

  /**
   * Gets the data of the vertex at the given index.
   * @param i The index of the desired vertex.
   * @return The data of the desired vertex.
   */
  def apply(i: I): V

  /**
   * Gets the data of the edge at the given pair of indices.
   * @param i The first index of the edge.
   * @param j The second index of the edge.
   * @return The label of the desired edge.
   */
  def apply(i: I, j: I): E

  /**
   * Gets the vertex object at the given index.
   * @param i The index of the vertex.
   * @return The vertex object.
   */
  def vertexAt(i: I): Vertex = Vertex(i)

  /**
   * Gets the edge object at the given pair of indices.
   * @param i The first index of the edge.
   * @param j The second index of the edge.
   * @return The desired edge object.
   */
  def edgeAt(i: I, j: I): Edge = Edge(i, j)

  /**
   * Gets the indices of all vertices.
   * @return A set of indices of all vertices.
   */
  def vertexIds: Set[I]

  /**
   * Gets the index pairs of all edges.
   * @return A set of index pairs of all edges.
   */
  def edgeIds: Set[(I, I)]

  /**
   * Gets the vertex objects of all vertices.
   * @return A set of all vertex objects of all vertices.
   */
  def vertices = vertexIds.view.map(i ⇒ vertexAt(i))

  /**
   * Gets the edge objects of all edges.
   * @return A set of edge objects of all edges.
   */
  def edges = edgeIds.view.map(p ⇒ edgeAt(p._1, p._2))

  /**
   * Gets the indices of the outgoing vertices of the vertex at the given index.
   * @param i The index of the vertex queried.
   * @return A set of indices of all outgoing vertices of the vertex queried.
   */
  def outgoingIdsOf(i: I): Set[I]

  /**
   * Gets the vertex objects of the outgoing vertices of the vertex at the given index.
   * @param i The index of the vertex queried.
   * @return A set of vertex objects of all outgoing vertices of the vertex queried.
   */
  def outgoingVerticesOf(i: I): Set[Vertex]

  /**
   * Gets the edge objects of the outgoing vertices of the vertex at the given index.
   * @param i The index of the vertex queried.
   * @return A set of edge objects of all outgoing vertices of the vertex queried.
   */
  def outgoingEdgesOf(i: I): Set[Edge]

  /**
   * The out degree of the vertex at the given index.
   * @param i The index of the vertex queried.
   * @return The out degree of the vertex queried.
   */
  def ougDegreeOf(i: I) = outgoingIdsOf(i).size

  def str(implicit f: StringFormatter[Graph[I, V, E]]) = f.str(this)

  def mapEdge[E2](f: E ⇒ E2): Graph[I, V, E2] = new Graph[I, V, E2] {
    override def apply(i: I): V = outer.apply(i)
    override def apply(i: I, j: I): E2 = f(outer.apply(i, j))

    override def edgeIds: Set[(I, I)] = outer.edgeIds
    override def vertexIds: Set[I] = outer.vertexIds

    override def outgoingVerticesOf(i: I): Set[Vertex] = outer.outgoingVerticesOf(i).map(v ⇒ Vertex(i))
    override def outgoingEdgesOf(i: I): Set[Edge] = outer.outgoingEdgesOf(i).map(e ⇒ Edge(e.i, e.j))
    override def outgoingIdsOf(i: I): Set[I] = outer.outgoingIdsOf(i)
  }

  def mapVertex[V2](f: V ⇒ V2): Graph[I, V2, E] = ???


  /**
   * Represents a vertex in the graph.
   * Contains both the index and the data of the vertex.
   * @param i The index of the vertex.
   */
  case class Vertex(i: I) extends Node[(I, V)] {
    override def data: (I, V) = (i, outer(i))
    override def succ: Iterable[Node[(I, V)]] = outer.outgoingVerticesOf(i)
    override def toString = i + " - " + outer(i)
  }

  /**
   * Represents an edge in the graph.
   * Contains both the indices and the data of the edge.
   * @param i The first index of the edge.
   * @param j The second index of the edge.
   */
  case class Edge(i: I, j: I) {
    def data = apply(i, j)
    def vi = vertexAt(i)
    def vj = vertexAt(j)
    override def toString = s"$vi --- $data --> $vj"
  }
}