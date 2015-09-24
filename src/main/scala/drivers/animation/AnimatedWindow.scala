package drivers.animation

import animation.Animation
import region.Region

/**
 * Created by rakesh.h on 18/09/15.
 */
trait AnimatedWindow extends App{
  val height = 600
  val width = 800

  def animate: Animation[Region]

  val frame = new AnimatedFrame(animate, width= width, height = height)
  frame.open()
}
