package me.yuhuan.collection

/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
trait Enumerator[+X] { outer ⇒
  def advance(): Boolean
  def current: X

  def map[Y](f: X ⇒ Y): Enumerator[Y] = new Enumerator[Y] {
    def advance(): Boolean = outer.advance()
    def current: Y = f(outer.current)
  }

  def flatMap[Y](f: X ⇒ Enumerator[Y]) = new Enumerator[Y] { inner ⇒
    var curEnumerator: Enumerator[Y] = Enumerator.empty
    def advance(): Boolean = {
      if (curEnumerator.advance()) true
      else {
        if (outer.advance()) {
          curEnumerator = f(outer.current)
          if (curEnumerator.advance()) true
          else inner.advance()
        }
        else false
      }
    }
    def current: Y = curEnumerator.current
  }

  def where(p: X ⇒ Boolean): Enumerator[X] = new Enumerator[X] {   // Y N N Y Y N N Y N
    def advance(): Boolean = {
      while (outer.advance()) if (p(outer.current)) return true
      false
    }
    def current: X = outer.current
  }

  def whereNot(p: X ⇒ Boolean): Enumerator[X] = where(a => !p(a))

  def append[Y >: X](y: Y): Enumerator[Y] = new Enumerator[Y] {
    var isInOriginalRange = true
    def advance(): Boolean = {
      if (outer.advance()) {
        return true
      }
      else {
        if (isInOriginalRange) {
          isInOriginalRange = false
          return true
        }
        else return false
      }
    }
    def current: Y = {
      if (isInOriginalRange) outer.current
      else y
    }
  }

}

object Enumerator {
  def empty: Enumerator[Nothing] = new Enumerator[Nothing] {
    def advance(): Boolean = false
    def current: Nothing = throw new NoSuchElementException()
  }
}