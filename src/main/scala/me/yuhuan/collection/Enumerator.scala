package me.yuhuan.collection


/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
trait Enumerator[+X] { outer ⇒
  def moveNext(): Boolean
  def current: X

  def head: X = ???
  def headOption: Option[X] = ???

  def tail: Enumerator[X] = ???

  def last: X = ???
  def lastOption: Option[X] = ???

  def init: Enumerator[X] = ???

  def map[Y](f: X ⇒ Y): Enumerator[Y] = new Enumerator[Y] {
    def moveNext(): Boolean = outer.moveNext()
    def current: Y = f(outer.current)
  }

  def fmap[Y](f: X ⇒ Enumerator[Y]) = flatMap(f)

  def flatMap[Y](f: X ⇒ Enumerator[Y]) = new Enumerator[Y] { inner ⇒
    var curEnumerator: Enumerator[Y] = Enumerator.empty
    def moveNext(): Boolean = {
      if (curEnumerator.moveNext()) true
      else {
        if (outer.moveNext()) {
          curEnumerator = f(outer.current)
          if (curEnumerator.moveNext()) true
          else inner.moveNext()
        }
        else false
      }
    }
    def current: Y = curEnumerator.current
  }

  def where(p: X ⇒ Boolean): Enumerator[X] = new Enumerator[X] {   // Y N N Y Y N N Y N
    def moveNext(): Boolean = {
      while (outer.moveNext()) if (p(outer.current)) return true
      false
    }
    def current: X = outer.current
  }

  def whereNot(p: X ⇒ Boolean): Enumerator[X] = where(a => !p(a))

  def groupBy[K, Y >: X](f: X ⇒ K): Map[K, Set[Y]] = ???

  def foldLeft[Y](y: Y)(f: (Y, X) ⇒ Y): Y = ???
  def foldRight[Y](f: (X, Y) ⇒ Y)(y: Y): Y = ???
  def fold[Y >: X](y: Y)(f: (Y, Y) ⇒ Y): Y = ???

  def reduceLeft[Y >: X](f: (Y, Y) ⇒ Y): Y = ???
  def reduceRight[Y >: X](f: (Y, Y) ⇒ Y): Y = ???

  def scanLeft[Y](y: Y)(f: (Y, X) ⇒ Y): Enumerator[Y] = ???
  def scanRight[Y](f: (X, Y) ⇒ Y)(y: Y): Enumerator[Y] = ???

  def prepend[Y >: X](y: Y): Enumerator[Y] = new Enumerator[Y] {
    var isInOriginalRange = false
    var cur: Y = _
    def moveNext(): Boolean = {
      if (!isInOriginalRange) {
        cur = y
        isInOriginalRange = true
        true
      }
      else {
        val outerHashNext = outer.moveNext()
        if (outerHashNext) cur = outer.current
        outerHashNext
      }

    }
    def current: Y = cur
  }

  def append[Y >: X](y: Y): Enumerator[Y] = new Enumerator[Y] {
    var isInOriginalRange = true
    def moveNext(): Boolean = {
      if (outer.moveNext()) true
      else {
        if (isInOriginalRange) {
          isInOriginalRange = false
          true
        }
        else false
      }
    }
    def current: Y = {
      if (isInOriginalRange) outer.current
      else y
    }
  }

  def take(n: Int): Enumerator[X] = new Enumerator[X] {
    var curIdx = 0
    def moveNext(): Boolean = {
      if (outer.moveNext() && curIdx < n) {
        curIdx += 1
        true
      }
      else false
    }
    def current: X = outer.current
  }

  def skip(n: Int): Enumerator[X] = new Enumerator[X] {

    var isFirstMove = true

    def moveNext(): Boolean = {
      if (isFirstMove) {
        isFirstMove = false
        var moveCount = n
        while (moveCount >= 0) {
          outer.moveNext()
          moveCount -= 1
        }
        true
      }
      else outer.moveNext()
    }

    def current: X = outer.current
  }

  def slice(start: Int, end: Int): Enumerator[X] = outer.skip(start).take(end - 1)

  def window(size: Int): Enumerator[X] = ???


}

object Enumerator {
  def empty: Enumerator[Nothing] = new Enumerator[Nothing] {
    def moveNext(): Boolean = false
    def current: Nothing = throw new NoSuchElementException()
  }
}