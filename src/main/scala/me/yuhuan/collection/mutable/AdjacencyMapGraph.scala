package me.yuhuan.collection.mutable

import me.yuhuan.collection.StructureMutableGraph
import me.yuhuan.collection.builder.GraphBuilder
import me.yuhuan.collection.factory.GraphFactory

import scala.collection._
import scala.language.higherKinds

/**
 * An implementation of an SMutableGraph.
 * @param nodeMap a map from the index to the data of all vertices.
 * @param edgeMap Edge like this:
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
 *                {
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
 * @tparam K The type of the index of an vertex.
 * @tparam N The type of the data in an vertex.
 * @tparam E The type of the data of an edge.
 */
class AdjacencyMapGraph[K, N, E] private(val nodeMap: mutable.HashMap[K, N], val edgeMap: mutable.HashMap[K, mutable.ListMap[K, E]])
  extends StructureMutableGraph[K, N, E] { outer ⇒

  def apply(i: K) = nodeMap(i)

  def apply(i: K, j: K) = edgeFromTo(i, j)

  def update(i: K, j: K, e: E): Unit = edgeMap(i)(j) = e

  def update(i: K, v: N): Unit = nodeMap(i) = v

  def nodeKeys = nodeMap.keys

  def edgeKeys = {

// WRITING IN THIS WAY IS WRONG
//    edgeData.flatMap(p ⇒ p._2.map(q ⇒ p._1 → q._1)).toSet

// INTENDED SEMANTIC:
//    val result = mutable.HashSet[(K, K)]()
//    for (p ← edgeData) {
//      for (q ← p._2) {
//        result.add(p._1 → q._1)
//      }
//    }
//
//    result.toSet


    edgeMap.toSeq.flatMap(p ⇒ p._2.toSeq.map(q ⇒ p._1 → q._1))

  }

  def outgoingNodeKeysOf(i: K): Iterable[K] = {
    if (!edgeMap.contains(i)) Nil
    else edgeMap(i).keys
  }

  override def outgoingEdgeKeysOf(i: K) = {
    if (!edgeMap.contains(i)) Nil
    else edgeMap(i).toSeq.map(p ⇒ i → p._1)
  }

  def edgeFromTo(i: K, j: K): E = {
    if (edgeMap contains i) {
      if (edgeMap(i) contains j) edgeMap(i)(j)
      else throw new Exception(s"v2 = $j has no edge!")
    }
    else throw new Exception(s"v1 = $i has no edge!")
  }

  def addEdge(e: (K, K, E)): Unit = addEdge(e._1, e._2, e._3)

  def addEdge(i: K, j: K, edge: E): Unit = {
    // Check if the from and to vertices are in the vertex set
    if (!nodeMap.contains(i))
      throw new Exception(s"Vertex $i does not exist! Add the $i to the graph first!")

    if (!nodeMap.contains(j))
      throw new Exception(s"Vertex $j does not exist! Add the $j to the graph first!")

    // Add the edge.
    if (edgeMap contains i) edgeMap(i)(j) = edge
    else edgeMap(i) = mutable.ListMap[K, E](j → edge)
  }

  def addNode(i: K, v: N): Unit = nodeMap(i) = v

  def addVertex(v: (K, N)): Unit = addNode(v._1, v._2)

  def removeVertexAt(i: K) = {
    // remove the vertex from _vertices
    nodeMap -= i

    // remove outgoing edges of the vertex
    edgeMap -= i

    // remove incoming edges of the vertex
    edgeMap.foreach(_._2 -= i)
  }

  def removeEdgeAt(i: K, j: K) = {
    edgeMap(i) -= j
  }

  override def clone(): AdjacencyMapGraph[K, N, E] = {
    val newVertexMap = outer.nodeMap.clone()
    val newEdgeData = mutable.HashMap[K, mutable.ListMap[K, E]]()

    for (p ← outer.edgeMap) {
      newEdgeData += p._1 → p._2.clone()
    }

    new AdjacencyMapGraph[K, N, E](newVertexMap, newEdgeData)
  }

  override def hashCode = 17 + nodeMap.hashCode * 23 + edgeMap.hashCode() * 23

}

object AdjacencyMapGraph extends GraphFactory[AdjacencyMapGraph] {

  override implicit def newBuilder[I, V, E] = new GraphBuilder[I, V, E, AdjacencyMapGraph[I, V, E]] {
    val nodeGraph = mutable.HashMap[I, V]()
    val edgeMap = mutable.HashMap[I, mutable.ListMap[I, E]]()

    val newGraph = new AdjacencyMapGraph[I, V, E](nodeGraph, edgeMap)
    override def addNode(i: I, v: V) = newGraph.addNode(i, v)
    override def addEdge(i: I, j: I, e: E) = newGraph.addEdge(i, j, e)
    override def result = newGraph
  }

}