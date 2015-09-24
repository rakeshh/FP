package drivers.animation

import java.awt.event.{ActionEvent, ActionListener}
import java.awt.{Graphics, Graphics2D}
import javax.swing.{JPanel, Timer}

import animation.Animation
import implicits.JavaImplicits._
import region.Region

/**
 * Created by rakesh.h on 20/08/15.
 */
class AnimatedCanvas(animate: Animation[Region])  extends JPanel with ActionListener{
  val timer = new Timer(20, this)
  val startTime = System.currentTimeMillis
  timer.start()

  override def actionPerformed(e: ActionEvent): Unit = repaint()

  override def paintComponent(g: Graphics): Unit = {
    implicit  val size  = (getWidth, getHeight)
    val g2D = g.asInstanceOf[Graphics2D]
    val currentTime = System.currentTimeMillis
    val time = (currentTime - startTime).toDouble/1000

    val region = animate.run(time)
    region.draw.run(g2D)
  }
}