package me.yuhuan.collection

/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
trait RootedGraph[K, +V, +E] extends Graph[K, V, E] with HasRoot[V]
