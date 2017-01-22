package drivers.shapes

import region.{Basic, Region}
import shape.Rectangle

/**
 * Created by rakesh.h on 18/09/15.
 * y = x2 curve , combine tiny width rectangles like in calculus.
 * Right click and run it.
 */
object Sin extends AbstractWindow{
  override def getShape: Region = {
   val xs = (-10.0 to 10.0 by .05)
   val ys = xs.map(x => Math.sin(x))
   val rectangles: Seq[Region] = (xs zip ys).map{
     case (x, y) => Basic(Rectangle(.1, y)) --> (x, y/2)
   }
    rectangles.reduce(_ ++ _)
  }
}
