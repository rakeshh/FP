package drivers

import region.{Basic, Region}
import shape.Rectangle

/**
 * Created by rakesh.h on 18/09/15.
 * y = x2 curve , combine tiny width rectangles like in calculus.
 * Right click and run it.
 */
object YX2 extends AbstractWindow{
  override def getShape: Region = {
   val xs = (0.0 to 4.0 by .01)
   val ys = xs.map(x => Math.pow(x, 2.0))
   val rectangles: Seq[Region] = (xs zip ys).map(x => Basic(Rectangle(.1, x._2)) --> (x._1, 0.0))
    rectangles.reduce(_ + _)
  }
}
