package drivers

import region.{Basic, Region}
import shape.Rectangle

/**
 * Created by rakesh.h on 18/09/15.
 * Right Click and run it. Two spokes.
 */
object Spokes extends AbstractWindow{
  override def getShape: Region = {
    val initialSpoke = Basic(Rectangle(2.0, .1))
    val rotated = Region.rotate(initialSpoke, 30)

    rotated + initialSpoke
  }
}
