/**
 * Created by Yuhuan Jiang (jyuhuan@gmail.com) on 7/20/15.
 */

import me.yuhuan.collection.graph._
import me.yuhuan.collection.Implicits._
import me.yuhuan.strategy.search._

object GraphTest extends App {

  val graph1 = AdjacencyMapGraph(
    0 → "A",
    1 → "B",
    2 → "C",
    3 → "D"
  )(
    0 → "a" → 1,
    0 → "ab" → 2,
    0 → "abc" → 3,
    2 → "abcd" → 1,
    3 → "abcde" → 1
  )

  val graph2 = graph1.mapEdges(e ⇒ e.length)
                     .mapVertices(v ⇒ v match {
                       case "A" ⇒ "N0"
                       case "B" ⇒ "N1"
                       case "C" ⇒ "N2"
                       case "D" ⇒ "N3"
                       case "E" ⇒ "N4"
                       case _ ⇒ "Unknown"
                     })

  val graph3 = graph1.zip(graph2)

  val dot1 = graph1.str
  val dot2 = graph2.str
  val dot3 = graph3.str

  import graph1.enableVertexSearching

  val pathAB = graph1.vertexAt(0) ~~> graph1.vertexAt(1)

  val bp = 0



}
