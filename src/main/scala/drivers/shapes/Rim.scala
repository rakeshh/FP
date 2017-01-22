package drivers.shapes

import region.{Basic, Region}
import shape.Circle

/**
  * Created by rakesh on 22/1/17.
  */
object Rim extends AbstractWindow{
  def getShape: Region = {
    val outer = Basic(Circle(1.0)) //1 cm
    val inner = Basic(Circle(.9)) //0.9 cm
    outer - inner
  }
}
