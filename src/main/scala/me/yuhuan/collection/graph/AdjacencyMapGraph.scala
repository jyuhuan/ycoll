package me.yuhuan.collection.graph

import scala.collection._
import scala.language.higherKinds

/**
 * An implementation of an SMutableGraph.
 * @param _vertices a map from the index to the data of all vertices.
 * @param edgeData Edge data.
 *
 *                 E.g.:
 *
 *                         L1
 *                     1 ------- 3
 *                 L2 /|       _/
 *                   / |L5   _/
 *                  0  |   _/ L3
 *                   \ | _/
 *                 L4 \|/
 *                     2
 *
 *                 The data structure for the graph above is:
 *
 *                 {
 *                   0: {
 *                     1: L2,
 *                     2: L4
 *                   },
 *                   1: {
 *                     0: L2,
 *                     2: L5,
 *                     3: L1
 *                   },
 *                     2: {
 *                     0: L4,
 *                     1: L5,
 *                     3: L3,
 *                   },
 *                     3: {
 *                     1: L1,
 *                     2: L3
 *                   }
 *                 }
 *
 * @tparam I The type of the index of an vertex.
 * @tparam V The type of the data in an vertex.
 * @tparam E The type of the data of an edge.
 */
class AdjacencyMapGraph[I, V, E](
  val _vertices: mutable.Map[I, V],
  edgeData: mutable.HashMap[I, mutable.ListMap[I, E]]
) extends SMutableGraph[I, V, E] { outer ⇒


  def apply(i: I) = _vertices(i)

  def apply(i: I, j: I) = edgeFromTo(i, j)

  def update(i: I, j: I, e: E): Unit = edgeData(i)(j) = e

  def update(i: I, v: V): Unit = _vertices(i) = v

  def vertexIds = _vertices.keySet

  def edgeIds = {

// WRITING IN THIS WAY IS WRONG
//    edgeData.flatMap(p ⇒ p._2.map(q ⇒ p._1 → q._1)).toSet

// INTENDED SEMANTIC:
//    val result = mutable.HashSet[(I, I)]()
//    for (p ← edgeData) {
//      for (q ← p._2) {
//        result.add(p._1 → q._1)
//      }
//    }
//
//    result.toSet


    edgeData.toSeq.flatMap(p ⇒ p._2.toSeq.map(q ⇒ p._1 → q._1)).toSet

  }

  def outgoingVertexIdsOf(i: I): Set[I] = {
    if (!edgeData.contains(i)) Set()
    else edgeData(i).keySet
  }

  override def outgoingEdgeIdsOf(i: I) = {
    if (!edgeData.contains(i)) Set()
    else edgeData(i).map(p ⇒ i → p._1).toSet
  }

  def edgeFromTo(i: I, j: I): E = {
    if (edgeData contains i) {
      if (edgeData(i) contains j) edgeData(i)(j)
      else throw new Exception(s"v2 = $j has no edge!")
    }
    else throw new Exception(s"v1 = $i has no edge!")
  }

  def addEdge(e: (I, I, E)): Unit = addEdge(e._1, e._2, e._3)

  def addEdge(i: I, j: I, edge: E): Unit = {
    // Check if the from and to vertices are in the vertex set
    if (!_vertices.contains(i))
      throw new Exception(s"Vertex $i does not exist! Add the $i to the graph first!")

    if (!_vertices.contains(j))
      throw new Exception(s"Vertex $j does not exist! Add the $j to the graph first!")

    // Add the edge.
    if (edgeData contains i) edgeData(i)(j) = edge
    else edgeData(i) = mutable.ListMap[I, E](j → edge)
  }

  def addVertex(i: I, v: V): Unit = _vertices(i) = v

  def addVertex(v: (I, V)): Unit = addVertex(v._1, v._2)

  def removeVertexAt(i: I) = {
    // remove the vertex from _vertices
    _vertices -= i

    // remove outgoing edges of the vertex
    edgeData -= i

    // remove incoming edges of the vertex
    edgeData.foreach(_._2 -= i)
  }

  def removeEdgeAt(i: I, j: I) = {
    edgeData(i) -= j
  }

  override def clone(): AdjacencyMapGraph[I, V, E] = {
    new AdjacencyMapGraph[I, V, E](outer._vertices.clone(), outer.edgeData.clone())
  }

}

object AdjacencyMapGraph extends GraphFactory[AdjacencyMapGraph] {

  override implicit def newBuilder[I, V, E] = new GraphBuilder[I, V, E, AdjacencyMapGraph[I, V, E]] {
    val edgeData = mutable.HashMap[I, mutable.ListMap[I, E]]()
    val vertexMap = mutable.HashMap[I, V]()
    val newGraph = new AdjacencyMapGraph[I, V, E](vertexMap, edgeData)

    override def addVertex(i: I, v: V) = newGraph.addVertex(i, v)
    override def addEdge(i: I, j: I, e: E) = newGraph.addEdge(i, j, e)

    override def result = newGraph
  }

}