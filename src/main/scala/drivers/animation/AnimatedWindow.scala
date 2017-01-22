package drivers.animation

import animation.Animation
import region.Region

/**
 * Created by rakesh.h on 18/09/15.
 */
trait AnimatedWindow {
  def animate: Animation[Region]

  def main(args: Array[String]): Unit = {
    val height = 600
    val width = 800
    val frame = new AnimatedFrame(animate, width= width, height = height)
    frame.open()
  }

}
