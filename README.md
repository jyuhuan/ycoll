# YColl

This library aims at providing data structures (especially collections) that the standard library of the Scala programming language does not provide. 

## Maven/SBT
The best way to use this library in your project is to add the following dependency to your `build.sbt` file:

    libraryDependencies += "me.yuhuan" % "ycoll_2.11" % "0.1.1"


## What's in Current Release

In the current release, a carefully designed representation for directed graphs, `Graph[I, V, E]`, is provided. Each **vertex** in the graph has an **index** and the corresponding **data**. Each **edge** in the graph has a **pair of vertex indices**, and the **data** it carries (usually the weight or the label of the edge). To provide maximum flexibility, the indices are generically typed as `I`, the vertex data are generically typed as `V`, and the edge data are generically typed as `E`.

### Example
Create a graph by:

```scala
val g = AdjacencyMapGraph(/* vertices */)(/* edges */)
```

To create the following graph, 

                L1
        (0,A) ------- (3,D)
     L2 / |           __/
       /  |        __/
    (1,B) |L5   __/
       \  |  __/  L3
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

To search for the path from vertex `B` to `D`, write:

```scala
val path = g.vertexAt(1) ~~> g.vertexAt(3)
```


## Road Map
* Bidirectional graph
* Undirected graph
* Multigraph (more than one edge can exist between two vertices)