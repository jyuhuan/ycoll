package me.yuhuan.collection.graph

import me.yuhuan.collection.HasRoot

/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
trait RootedGraph[@specialized(Int) I, +V, +E] extends Graph[I, V, E] with HasRoot[V]
