package drivers.shapes

import region.{Basic, Region}
import shape.Circle

/**
 * Created by rakesh.h on 17/09/15.
 * Right click and run it.
 */
object FirstDriver extends AbstractWindow{
  override def getShape: Region = {
    Basic(Circle(3.0))
  }
}
