package drivers.animation

import animation.{Animate, Animation}
import region.{Basic, Region}
import shape.{Circle, Rectangle}

/**
  * Created by rakesh on 22/1/17.
  */
object RotatingTyre extends AnimatedWindow{
  def animate: Animation[Region] = Animate{time => getTyre(time) }

  def getTyre(time: Double): Region = {
    val outer = Basic(Circle(1.0)) //1 cm
    val inner = Basic(Circle(.9)) //0.9 cm
    val rim  = outer - inner
    val initialAngle = time * 30
    val spoke = Basic(Rectangle(2.0, .1))
    val rotatedSpokes = (0 to 6).map(i => Region.rotate(spoke, -initialAngle + i * 30))
    val spokes = rotatedSpokes.reduce(_ ++ _)

    (rim ++ spokes)
  }
}
