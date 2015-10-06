package me.yuhuan.collection

/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
trait Readable[+X] extends HasForeach[X] { outer ⇒

  def newEnumerator: Reader[X]

  def foreach[Y](f: (X ⇒ Y)): Unit = {
    val e = outer.newEnumerator
    while (true) {
      e.read match {
        case Some(x) ⇒ f(x)
        case None ⇒ return
      }
    }
  }

  def map[Y](f: (X ⇒ Y)): Readable[Y] = new Readable[Y] {
    val e = outer.newEnumerator
    def newEnumerator: Reader[Y] = new Reader[Y] {
      def read: Option[Y] = e.read.map(f)
    }
  }

  def flatMap[Y](f: (X ⇒ Readable[Y])) = new Readable[Y] {
    val e: Reader[X] = outer.newEnumerator
    var ə: Reader[Y] = Reader.empty
    def newEnumerator: Reader[Y] = new Reader[Y] { inner ⇒
      def read: Option[Y] = ə.read match {
        case Some(y) ⇒ Some(y)
        case None ⇒ e.read match {
          case Some(x) ⇒ {
            ə = f(x).newEnumerator
            ə.read match {
              case Some(y) ⇒ Some(y)
              case None ⇒ inner.read
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
