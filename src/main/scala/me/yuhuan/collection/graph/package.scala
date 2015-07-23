package me.yuhuan.collection

import me.yuhuan.strategy.format.StringFormatter

/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
package object graph {
  /**
   * A string formatter for [[me.yuhuan.collection.graph.Graph Graph]]s.
   */
  implicit def stringFormatter4Graph[I, V, E]: StringFormatter[Graph[I, V, E]] = new StringFormatter[Graph[I, V, E]] {

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
        val id = v.data._1
        val data = v.data._2
        sb append dotOfVertex(id.toString, data.toString)
      })

      x.edges.foreach(e ⇒ {
        val id1 = e.i
        val id2 = e.j
        val data = e.data
        sb append dotOfEdge(id1.toString, id2.toString, data.toString)
      })

      sb append "}"
      sb.toString()
    }
  }
}
