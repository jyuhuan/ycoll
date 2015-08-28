package me.yuhuan.collection

/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
trait Enumerable[+X] extends HasForeach[X] { outer ⇒

  def newEnumerator: Enumerator[X]

  def foreach[Y](f: (X ⇒ Y)): Unit = {
    val e = newEnumerator
    while (e.moveNext()) {
      f(e.current)
    }
  }

  def map[Y](f: X ⇒ Y): Enumerable[Y] = new Enumerable[Y] {
    def newEnumerator: Enumerator[Y] = outer.newEnumerator.map(f)
  }

  def flatMap[Y](f: X ⇒ Enumerable[Y]) = new Enumerable[Y] {
    def newEnumerator: Enumerator[Y] = outer.newEnumerator.flatMap[Y](x ⇒ f(x).newEnumerator)
  }

  def where(p: X ⇒ Boolean) = new Enumerable[X] {
    def newEnumerator: Enumerator[X] = outer.newEnumerator.where(p)
  }

  def whereNot(p: X ⇒ Boolean) = where(a => !p(a))

  def prepend[Y >: X](y: Y): Enumerable[Y] = new Enumerable[Y] {
    def newEnumerator: Enumerator[Y] = outer.newEnumerator.prepend(y)
  }

  def append[Y >: X](y: Y): Enumerable[Y] = new Enumerable[Y] {
    def newEnumerator: Enumerator[Y] = outer.newEnumerator.append(y)
  }

  def take(n: Int): Enumerable[X] = new Enumerable[X] {
    def newEnumerator: Enumerator[X] = outer.newEnumerator.take(n)
  }

  def skip(n: Int): Enumerable[X] = new Enumerable[X] {
    def newEnumerator: Enumerator[X] = outer.newEnumerator.skip(n)
  }

  def slice(start: Int, end: Int): Enumerable[X] = new Enumerable[X] {
    def newEnumerator: Enumerator[X] = outer.newEnumerator.slice(start, end)
  }

  def window(size: Int): Enumerable[X] = new Enumerable[X] {
    def newEnumerator: Enumerator[X] = ???
  }

  def str: String = {
    val sb = new StringBuilder()
    outer.foreach(x ⇒ {
      sb append x
      sb append ", "
    })
    sb.toString()
  }
}
