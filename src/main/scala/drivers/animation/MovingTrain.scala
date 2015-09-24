package drivers.animation

import animation.Animate
import drivers.util.Train
import region.Region
import scala.math.Pi

/**
 * Created by rakesh.h on 18/09/15.
 * Moving train, right click and run it.
 */
object MovingTrain extends AnimatedWindow{
  override def animate: Animate[Region] = Animate {time =>
    val angle = 180/ Pi
    Train.create(-angle * time, time)
  }
}
