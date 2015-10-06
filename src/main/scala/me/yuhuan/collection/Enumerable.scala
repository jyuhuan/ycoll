package me.yuhuan.collection

/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
trait Enumerable[+X] extends HasForeach[X] { outer ⇒

  def newEnumerator: Enumerator[X]

  def foreach[Y](f: (X ⇒ Y)): Unit = {
    val e = outer.newEnumerator
    while (true) {
      e.next match {
        case Some(x) ⇒ f(x)
        case None ⇒ return
      }
    }
  }

  def map[Y](f: (X ⇒ Y)): Enumerable[Y] = new Enumerable[Y] {
    val e = outer.newEnumerator
    def newEnumerator: Enumerator[Y] = new Enumerator[Y] {
      def next: Option[Y] = e.next.map(f)
    }
  }

  def flatMap[Y](f: (X ⇒ Enumerable[Y])) = new Enumerable[Y] {
    val e: Enumerator[X] = outer.newEnumerator
    var ə: Enumerator[Y] = Enumerator.empty
    def newEnumerator: Enumerator[Y] = new Enumerator[Y] { inner ⇒
      def next: Option[Y] = ə.next match {
        case Some(y) ⇒ Some(y)
        case None ⇒ e.next match {
          case Some(x) ⇒ {
            ə = f(x).newEnumerator
            ə.next match {
              case Some(y) ⇒ Some(y)
              case None ⇒ inner.next
            }
          }
          case None ⇒ None
        }
      }
    }
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
