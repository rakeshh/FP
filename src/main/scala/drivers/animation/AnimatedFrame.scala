package drivers.animation

import javax.swing.JFrame

import animation.Animation
import region.Region

/**
 * Created by rakesh.h on 20/08/15.
 */
class AnimatedFrame(animate: Animation[Region], x: Int = 0, y: Int = 0, width: Int = 800, height: Int = 600) {

  val frame = new JFrame
  frame.setBounds(x, y, width, height)
  frame.add(new AnimatedCanvas(animate))

  def open(): Unit = frame.setVisible(true)

}

