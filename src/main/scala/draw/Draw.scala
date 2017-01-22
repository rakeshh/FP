package draw

import java.awt.Graphics2D

/**
 * Created by rakesh.h on 14/09/15.
 */
sealed trait Draw[C] { self =>
  def run(canvas: C): Unit = self match {
    case Drawing(f) => f(canvas)
  }
}
case class Drawing[C](f: C => Unit) extends Draw[C]
