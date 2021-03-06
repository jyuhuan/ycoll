package me.yuhuan.collection

/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
trait RootedGraph[K, +N, +E] extends Graph[K, N, E] with HasRoot[N]
