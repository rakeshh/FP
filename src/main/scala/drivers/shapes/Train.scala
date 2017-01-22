package drivers.shapes

import java.awt.geom.Area

import implicits.JavaImplicits._
import region._
import shape.{Circle, Rectangle}

/**
 * Created by rakesh.h on 17/09/15.
 * Right click and run it.
 */
object Train extends AbstractWindow{
  override def getShape: Region = {
    //we will create 5 coaches that have been moved to app distance and then add them together
    val coaches = (-1 to 4).map(index => moveCoachTo(coach, index * 8.5))
    val train = coaches.reduce[Region](_ ++ _)
    train
  }

  //it will move coach back/forward by this distance along x axis
  def moveCoachTo(coach: Region, distance: Double) = coach --> (distance, 0.0)

  def coach: Region = {
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
