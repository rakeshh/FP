package drivers.shapes

import region.{Basic, Region}
import shape.{Circle, Rectangle}

/**
  * Created by rakesh on 22/1/17.
  */
object Coach extends AbstractWindow{
  def getShape: Region = {
    val backTyre = tyre
    val frontTyre = backTyre --> (5.0, 0.0) //move fwd by 5 cm

    val upperBody = Basic(Rectangle(8, 3))
    val upperBodyLifted = upperBody --> (2.5, 2.5) //lift the upper body up and little fwd

    upperBodyLifted ++ tyre ++ frontTyre
  }

  def tyre: Region = {
    val outer = Basic(Circle(1.0))
    val inner = Basic(Circle(.9))
    val rim = outer - inner
    val spoke:Region = Basic(Rectangle(2.0, .1))
    val rotatedSpokes = (0 to 6).map(i => Region.rotate(spoke, i * 30))
    val spokes = rotatedSpokes.reduce(_ ++ _)
    rim ++ spokes
  }
}
