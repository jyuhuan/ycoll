package me.yuhuan.collection.graph

/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
trait GraphBuilder[I, V, E] {
  def addVertex(i: I, v: V)
  def addEdge(i: I, j: I, e: E)

  def +=(v: (I, V)) = addVertex(v._1, v._2)
  def +=(e: (I, I, E)) = addEdge(e._1, e._2, e._3)

  def addVertices(vs: (I, V)*) = vs foreach +=
  def addEdges(es: (I, I, E)*) = es foreach +=

  def result: Graph[I, V, E]
}
