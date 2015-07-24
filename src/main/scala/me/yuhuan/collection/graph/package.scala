package me.yuhuan.collection

import me.yuhuan.strategy.format.StringFormatter

/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
package object graph {
  /**
   * A string formatter for [[me.yuhuan.collection.graph.Graph Graph]]s.
   */
  implicit def plainStringFormatter4Graph[I, V, E]: StringFormatter[Graph[I, V, E]] = new StringFormatter[Graph[I, V, E]] {

    def dotOfVertex(id: String, label: String) = {
      if (label == "") id + ";"
      else id + " [label=<" + label + ">];"
    }

    def dotOfEdge(fromId: String, toId: String, label: String) = {
      if (label == "") fromId + " -> " + toId + ";"
      else fromId + " -> " + toId + " [label=<" + label + ">];"
    }

    override def str(x: Graph[I, V, E]): String = {
      val sb = new StringBuilder()
      sb append "digraph { \n"

      x.vertices.foreach(v ⇒ {
        val id = v.id
        val data = v.data
        sb append dotOfVertex(id.toString, data.toString)
      })

      x.edges.foreach(e ⇒ {
        val id1 = e.id1
        val id2 = e.id2
        val data = e.data
        sb append dotOfEdge(id1.toString, id2.toString, data.toString)
      })

      sb append "}"
      sb.toString()
    }
  }

  def richStringFormatter4Graph[I, V, E, Style](format: Map[I, Style]): StringFormatter[Graph[I, V, E]] = ???
}
