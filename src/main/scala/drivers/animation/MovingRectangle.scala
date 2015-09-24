package drivers.animation

import animation.Animation
import region.{Basic, Region}
import shape.Rectangle

/**
 * Created by rakesh.h on 18/09/15.
 * Moving rectangle, right click and run it.
 */
object MovingRectangle extends AnimatedWindow{
  override def animate: Animation[Region] = {
    val rect = Basic(Rectangle(2, 1))
    val movingRectangle = Animation[Region]{time =>
      rect --> (time * 1, 0.0)
    }
    movingRectangle
  }
}
