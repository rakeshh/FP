package drivers.animation

import animation.{Animate, Animation}
import region.{Basic, Region}
import shape.{Circle, Rectangle}

/**
  * Created by rakesh on 22/1/17.
  */
object MovingCoach extends AnimatedWindow{
  def animate: Animation[Region] = Animate { time => getCoach(time)}

  def getCoach(time: Double): Region = {
    val backTyre = getTyre(time)
    val frontTyre = backTyre --> (5.0, 0.0) //move fwd by 5 cm

    val upperBody = Basic(Rectangle(8, 3))
    val upperBodyLifted = upperBody --> (2.5, 2.5) //lift the upper body up and little fwd, it is not moving
    val movingBody = upperBodyLifted --> (1 * time, 0)//it moves with time
    movingBody ++ backTyre ++ frontTyre
  }

  def getTyre(time: Double): Region = {
    val outer = Basic(Circle(1.0)) //1 cm
    val inner = Basic(Circle(.9)) //0.9 cm
    val rim  = outer - inner

    val initialAngle = (360/ (2 * Math.PI * 1)) * time //rotate so that it completes 1 rotation in 2 * pi * r seconds
    val positionXAxis = time * 1 //we move fwd by 1cm/sec

    val spoke = Basic(Rectangle(2.0, .1))
    val rotatedSpokes = (0 to 6).map(i => Region.rotate(spoke, -initialAngle + i * 30))
    val spokes = rotatedSpokes.reduce(_ ++ _)

    (rim ++ spokes) --> (positionXAxis, 0.0) //move tyre to new position
  }
}
