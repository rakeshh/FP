package drivers.util

import region.{Region, Basic}
import shape.{Rectangle, Circle}

/**
 * Created by rakesh.h on 18/09/15.
 * Train with initial angle and distance.
 */
object Train {

  def create(angle: Double, distance: Double): Region = {
    val outer = Basic(Circle(1.0))
    val inner = Basic(Circle(.9))
    val rim = outer - inner

    val spoke:Region = Basic(Rectangle(2.0, .1))
    val rotatedSpokes = (0 to 6).map(i => Region.rotate(spoke, angle + i * 30))
    val spokes = rotatedSpokes.reduce((spoke1, spoke2) => spoke1 + spoke2)
    // or in short : val spokes = rotatedSpokes.reduce(_ + _)

    val tyre = rim + spokes
    val frontTyre = tyre --> (5.0, 0.0) //move fwd by 5 cm

    val upperBody = Basic(Rectangle(8, 3))
    val upperBodyLifted = upperBody --> (2.5, 2.5) //lift the upper body up and little fwd

    val coach = upperBodyLifted + tyre + frontTyre
    val coaches = (1 to 5).map(index => coach --> (-index * 8.5, 0.0))

    val train = coaches.reduce[Region](_ + _)
    // val train = coaches.reduce[Region]((coach1, coach2) => coach1 + coach2)
    //train --> (10, 0.0) we did this to show what was in document
    train --> (distance, 0.0)
  }

}
