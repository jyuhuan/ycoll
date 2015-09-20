package me.yuhuan.collection.conversion
import me.yuhuan.collection.{Enumerable, Enumerator, default}

/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
object JavaConversions {

  implicit def javaIteratorAsYEnumerator[X](javaIterator: java.util.Iterator[X]): Enumerator[X] = new Enumerator[X] {
    def moveNext(): Boolean = {
      if (javaIterator.hasNext) {
        current = javaIterator.next()
        return true
      }
      false
    }
    var current: X = default[X]
  }

  implicit def javaIterableAsYEnumerable[X](javaIterable: java.lang.Iterable[X]): Enumerable[X] = new Enumerable[X] {
    def newEnumerator: Enumerator[X] = javaIteratorAsYEnumerator(javaIterable.iterator())
  }



}
