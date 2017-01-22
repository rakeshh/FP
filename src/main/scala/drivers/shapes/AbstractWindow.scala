package drivers.shapes

import region.Region

/**
 * Created by rakesh.h on 17/09/15.
 */
trait AbstractWindow{
  import implicits.JavaImplicits._

  def main(args: Array[String]): Unit = {
    val height = 600
    val width = 800
    implicit  val size = (width, height)
    val window = new DrawableWindow(getShape.draw, width = width, height = height)
    window.open()
  }


  def getShape: Region
}
