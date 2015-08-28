package me.yuhuan.collection.basicimp

import me.yuhuan.collection._

import scala.reflect.ClassTag

/**
 * @author Yuhuan Jiang (jyuhuan@gmail.com).
 */
class ResizableArray[X] extends Enumerable[X] {
  var data = new scala.collection.mutable.ArrayBuffer[AnyRef]()

  def newEnumerator: Enumerator[X] = new Enumerator[X] {
    var i = -1

    def moveNext(): Boolean = {
      i += 1
      i < data.length
    }

    def current: X = data(i).asInstanceOf[X]
  }
}

object ResizableArray {
  def apply[X](xs: X*) = {
    val result = new ResizableArray[X]()
    result.data = scala.collection.mutable.ArrayBuffer[AnyRef]()
    xs.foreach(x ⇒ result.data += x.asInstanceOf[AnyRef])
    result
  }

  def ofLength[X: ClassTag](length: Int): ResizableArray[X] = {
    val result = new ResizableArray[X]()
    result.data = scala.collection.mutable.ArrayBuffer[AnyRef]()
    (0 until length).foreach(i ⇒ result.data += null)
    result
  }

}
