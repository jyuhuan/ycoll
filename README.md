# YColl

This library aims at providing data structures (especially collections) that the standard library of the Scala programming language does not provide. 

> **WARNING**: This library is still under development. You may toy with the untested/unverified code, but please do **NOT** try to use this snapshot version in any actual work. 

The best way to use this library in your project is to add the following dependency to your `build.sbt` file:

```scala
resolvers += Resolver.sonatypeRepo("snapshots")
libraryDependencies += "me.yuhuan" %% "ycoll" % "0.0.0-SNAPSHOT"
```

## Features

In the current snapshot, a carefully designed representation for directed graphs, `Graph[I, V, E]`, is provided. Each **vertex** in the graph has an **index** and the corresponding **data**. Each **edge** in the graph has a **pair of vertex indices**, and the **data** it carries (typically the *weight* or the *label* of the edge). To provide maximum flexibility, the indices are generically typed as `I`, the vertex data are generically typed as `V`, and the edge data are generically typed as `E`. To provide as much efficiency as possible, `I` is specialized for `Int`, the most used type of index in many practical cases.

### Examples

#### Creating a Graph
Create a graph by:

```scala
val g = AdjacencyMapGraph(/* vertices */)(/* edges */)
```

To create the following graph, 

              L1
        (0,A)---(3,D)
     L2 / |     /
       /  |L5  /
    (1,B) |   /
       \  |  /  L3
     L4 \ | /
        (2,C)

write:

```scala
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
```

#### Searching
Since a `StateSpace` is defined on each vertex, one can easily perform various types of search on the graph. To use the default breadth-first graph search to find the path from vertex `B` (whose index is `1`) to `D` (whose index is `3`), simply write:

```scala
val path = g.vertexAt(1) ~~> g.vertexAt(3)
```

#### Tranforming a Graph
Some higher-order functions are provided at the level of the `Graph` trait. The higher order functions are by design *lazy*. Examples:

```scala
// Transforming edges and vertices
val g1 = g.mapEdges(s ⇒ s.replace("L", s.length))
val g2 = g.mapVertices(s ⇒ s"Node $s")

// Filtering edges and vertices.
val g3 = g.filterEdges(s ⇒ Set("L2", "L1") contains s)
val g4 = g.filterVertices(s ⇒ Set("A", "B") contains s)

```

#### Graphviz Compatibility
One can easily output any `Graph` to a `dot` format. Simply invoke `str` on anything that is a `Graph`. 

```scala
val dot = g.str
```

Notice that the formatter is actually implemented somewhere else rather than inside the graph, so an extra import line should be added to your code, if you want `str` to work.

```scala
import me.yuhuan.collection.graph._
```

## Road Map
* Bidirectional graph
* Undirected graph
* Multigraph (more than one edge can exist between two vertices)