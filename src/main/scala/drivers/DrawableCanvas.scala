package drivers

import java.awt.{Graphics, Graphics2D}
import javax.swing.JPanel

import draw.Draw

/**
 * Created by rakesh.h on 19/08/15.
 */
class DrawableCanvas(draw: Draw[Graphics2D]) extends JPanel{
  override def paintComponent(g: Graphics): Unit = {
    val g2D = g.asInstanceOf[Graphics2D]
    draw.run(g2D)
  }
}
