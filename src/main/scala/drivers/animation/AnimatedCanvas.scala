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
   g.clearRect(0, 0, getWidth, getHeight) //clear earlier drawn shapes
    val time = getTime
    val region = animate.run(time) //ask animation for the current position of shape

    implicit  val size  = (getWidth, getHeight)
    val g2D = g.asInstanceOf[Graphics2D]
    region.draw.run(g2D) //draw the shape
  }

  def getTime = {
    val currentTime = System.currentTimeMillis
    (currentTime - startTime).toDouble/1000 //time  in seconds
  }
}