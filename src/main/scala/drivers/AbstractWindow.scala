package drivers

import region.Region

/**
 * Created by rakesh.h on 17/09/15.
 */
trait AbstractWindow extends App{
  import implicits.JavaImplicits._
  val height = 600
  val width = 800
  implicit  val size = (width, height)

  val shape:Region = getShape
  val window = new DrawableWindow(shape.draw, width = width, height = height)
  window.open()

  def getShape: Region
}
