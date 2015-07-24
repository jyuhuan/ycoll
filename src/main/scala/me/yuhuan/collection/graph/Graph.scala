package me.yuhuan.collection.graph

import me.yuhuan.collection.node.Node
import me.yuhuan.strategy.format.StringFormatter

import scala.collection.Set
import scala.language.higherKinds
import scala.annotation.unchecked.{uncheckedVariance => uv}


/**
 * Represents a directed graph.
 *
 * @tparam I The type of the index of an vertex.
 * @tparam V The type of the data in an vertex.
 * @tparam E The type of the data of an edge.
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
  def outgoingVertexIdsOf(i: I): Set[I]

  /**
   *
   * @param i
   * @return
   */
  def outgoingEdgeIdsOf(i: I): Set[(I, I)]

  /**
   * Gets the vertex objects of the outgoing vertices of the vertex at the given index.
   * @param i The index of the vertex queried.
   * @return A set of vertex objects of all outgoing vertices of the vertex queried.
   */
  def outgoingVerticesOf(i: I): Set[Vertex] = outgoingVertexIdsOf(i).map(v ⇒ vertexAt(v))

  /**
   * Gets the edge objects of the outgoing vertices of the vertex at the given index.
   * @param i The index of the vertex queried.
   * @return A set of edge objects of all outgoing vertices of the vertex queried.
   */
  def outgoingEdgesOf(i: I): Set[Edge] = outgoingEdgeIdsOf(i).map(e ⇒ edgeAt(e._1, e._2))

  /**
   * The out degree of the vertex at the given index.
   * @param i The index of the vertex queried.
   * @return The out degree of the vertex queried.
   */
  def ougDegreeOf(i: I) = outgoingVertexIdsOf(i).size

  def str(implicit f: StringFormatter[Graph[I, V, E]]) = f.str(this)


  def mapVertices[V2](f: V ⇒ V2): Graph[I, V2, E] = new Graph[I, V2, E] {
    override def apply(i: I): V2 = f(outer.apply(i))
    override def apply(i: I, j: I): E = outer.apply(i, j)

    override def edgeIds: Set[(I, I)] = outer.edgeIds
    override def vertexIds: Set[I] = outer.vertexIds

    override def outgoingEdgeIdsOf(i: I) = outer.outgoingEdgeIdsOf(i)
    override def outgoingVertexIdsOf(i: I): Set[I] = outer.outgoingVertexIdsOf(i)
  }

  def mapEdges[E2](f: E ⇒ E2): Graph[I, V, E2] = new Graph[I, V, E2] {
    override def apply(i: I): V = outer.apply(i)
    override def apply(i: I, j: I): E2 = f(outer.apply(i, j))

    override def edgeIds: Set[(I, I)] = outer.edgeIds
    override def vertexIds: Set[I] = outer.vertexIds

    override def outgoingEdgeIdsOf(i: I) = outer.outgoingEdgeIdsOf(i)
    override def outgoingVertexIdsOf(i: I): Set[I] = outer.outgoingVertexIdsOf(i)
  }


  def filterVertices(f: V ⇒ Boolean): Graph[I, V, E] = new Graph[I, V, E] {
    // TODO: Wrong. Must prevent from accessing vertices that fails f. But other functions need it to be this way.
    override def apply(i: I): V = outer.apply(i)

    override def apply(i: I, j: I) = outer.apply(i, j)

    // TODO: change both to lazy. Need to redefine the return value of edgeIds as a lazy container.
    override def edgeIds: Set[(I, I)] = outer.edgeIds.filter(p ⇒ f(apply(p._1)) && f(apply(p._2)))
    override def vertexIds: Set[I] = outer.vertexIds.filter(p ⇒ f(apply(p)))

    override def outgoingEdgeIdsOf(i: I) = outer.outgoingEdgeIdsOf(i)
    override def outgoingVertexIdsOf(i: I): Set[I] = outer.outgoingVertexIdsOf(i)
  }

  def filterEdges(f: E ⇒ Boolean): Graph[I, V, E] = new Graph[I, V, E] {
    override def apply(i: I): V = outer.apply(i)

    // TODO: Wrong. Must prevent from accessing edges that fails f. But other functions need it to be this way.
    override def apply(i: I, j: I) = outer.apply(i, j)

    // TODO: change this to lazy. Need to redefine the return value of edgeIds as a lazy container.
    override def edgeIds: Set[(I, I)] = outer.edgeIds.filter(p ⇒ f(apply(p._1, p._2)))
    override def vertexIds: Set[I] = outer.vertexIds

    override def outgoingEdgeIdsOf(i: I) = outer.outgoingEdgeIdsOf(i)
    override def outgoingVertexIdsOf(i: I): Set[I] = outer.outgoingVertexIdsOf(i)
  }

  def to[G[_, _, _]](implicit builder: GraphBuilder[I, V @uv, E @uv, G[I, V @uv, E @uv]]): G[I, V @uv, E @uv] = {
    val b = builder
    b.addVertices(this.vertexIds.map(i ⇒ i → this(i)).toSeq: _*)
    b.addEdges(this.edgeIds.map(p ⇒ (p._1, p._2, this(p._1, p._2))).toSeq: _*)
    b.result
  }



  /**
   * Represents a vertex in the graph.
   * Contains both the index and the data of the vertex.
   * @param id The index of the vertex.
   */
  case class Vertex(id: I) extends Node[V] {
    override def data: V = outer(id)
    override def succ: Iterable[Node[V]] = outer.outgoingVerticesOf(id)
    override def toString = id + " - " + outer(id)
  }

  /**
   * Represents an edge in the graph.
   * Contains both the indices and the data of the edge.
   * @param id1 The first index of the edge.
   * @param id2 The second index of the edge.
   */
  case class Edge(id1: I, id2: I) {
    def data = apply(id1, id2)
    def vi = vertexAt(id1)
    def vj = vertexAt(id2)
    override def toString = s"$vi --- $data --> $vj"
  }
}