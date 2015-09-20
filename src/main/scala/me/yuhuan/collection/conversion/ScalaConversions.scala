package me.yuhuan.collection.conversion

import me.yuhuan.collection._

/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
object ScalaConversions {

  implicit def scalaIteratorToYEnumerator[X](scalaIterator: scala.Iterator[X]): Enumerator[X] = new Enumerator[X] {
    def moveNext(): Boolean = {
      if (scalaIterator.hasNext) {
        current = scalaIterator.next()
        return true
      }
      false
    }
    var current: X = default[X]
  }

  implicit def scalaIterableToYEnumerable[X](scalaIterable: scala.Iterable[X]): Enumerable[X] = new Enumerable[X] {
    def newEnumerator: Enumerator[X] = scalaIteratorToYEnumerator(scalaIterable.iterator)
  }

}
