package drivers.shapes

import region.{Basic, Region}
import shape.Rectangle

/**
 * Created by rakesh.h on 18/09/15.
 * Right Click and run it. Two spokes.
 */
object Spokes extends AbstractWindow{
  override def getShape: Region = {
    val spoke = Basic(Rectangle(2.0, .1))
    val rotatedSpokes = (0 to 6).map(i => Region.rotate(spoke, i * 30))
    rotatedSpokes.reduce(_ ++ _)
  }
}
