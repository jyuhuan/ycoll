/**
 * Created by Yuhuan Jiang (jyuhuan@gmail.com) on 7/20/15.
 */

import me.yuhuan.collection._
import me.yuhuan.collection.implementation.AdjacencyMapGraph

object GraphTest extends App {

  val graph1 = AdjacencyMapGraph[Int, String, Double](
    0 → "S",
    1 → "A",
    2 → "B",
    3 → "C",
    4 → "D",
    5 → "E",
    6 → "G"
  )(
    (0, 1, 1.5),
    (1, 4, 2.0),
    (1, 2, 2.0),
    (4, 5, 3.0),
    (2, 3, 3.0),
    (5, 6, 2.0),
    (3, 6, 4.0)
  )


  val s = graph1.str




  val bp = 0



}
