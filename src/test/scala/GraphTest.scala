/**
 * Created by Yuhuan Jiang (jyuhuan@gmail.com) on 7/20/15.
 */

import me.yuhuan.collection.graph._
import me.yuhuan.collection.Implicits._
import me.yuhuan.strategy.search._

object GraphTest extends App {

  //               L1
  //       (0,A) ------- (3,D)
  //    L2 / |           __/
  //      /  |        __/
  //   (1,B) |L5   __/
  //      \  |  __/  L3
  //    L4 \ | /
  //       (2,C)


  val g = AdjacencyMapGraph(
    0 → "A",
    1 → "B",
    2 → "C",
    3 → "D"
  )(
    0 → "L2" → 1,
    0 → "L5" → 2,
    0 → "L1" → 3,
    1 → "L2" → 0,
    1 → "L4" → 2,
    2 → "L5" → 0,
    2 → "L4" → 1,
    2 → "L3" → 3,
    3 → "L1" → 0,
    3 → "L3" → 2
  )
  val s = g.str

  val g1 = g.mapEdges(s ⇒ s.replace("L", "边"))
  val s1 = g1.str

  val g2 = g.mapVertices(s ⇒ s"Node $s")
  val s2 = g2.str

  val g3 = g.filterEdges(s ⇒ Set("L2", "L1") contains s)
  val s3 = g3.str

  val g4 = g.filterVertices(s ⇒ Set("A", "B") contains s)
  val s4 = g4.str

  val bp = 0



}
