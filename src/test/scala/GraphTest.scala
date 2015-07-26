/**
 * Created by Yuhuan Jiang (jyuhuan@gmail.com) on 7/20/15.
 */

import me.yuhuan.collection.graph.AdjacencyMapGraph
import me.yuhuan.collection.Implicits._
import me.yuhuan.strategy.search._

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
    0 → 1.5 → 1,
    0 → 2.0 → 4,
    1 → 2.0 → 2,
    4 → 3.0 → 5,
    2 → 3.0 → 3,
    5 → 2.0 → 6,
    3 → 4.0 → 6
  )





  val heuristics = Map[Int, Double](
    0 → 100,
    1 → 4,
    2 → 2,
    3 → 4,
    4 → 4.5,
    5 → 2,
    6 → 0
  )

  implicit def ssh = new StateSpaceWithCostWithHeuristic[graph1.Vertex] {

    override def h(x: graph1.Vertex): Double = heuristics(x.id)

    override def cost(from: graph1.Vertex, to: graph1.Vertex): Double = graph1(from.id, to.id)

    override def succ(state: graph1.Vertex): Iterable[graph1.Vertex] = graph1.outgoingVertexIdsOf(state.id).map(n ⇒ graph1.Vertex(n))
  }

  val pathAB = graph1.vertexAt(0) ~~> graph1.vertexAt(6)


  val bp = 0



}
