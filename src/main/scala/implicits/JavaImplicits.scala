package implicits

import java.awt.geom.{AffineTransform, Area => JRegion, Ellipse2D}
import java.awt.{Graphics2D, Polygon => JPolygon}

import draw.{Draw, Drawing}
import region._
import shape.{Shape => _, _}
import shape.{Shape => PlainShape}

/**
 * Created by rakesh.h on 17/09/15.
 */
object JavaImplicits {
  type Point = (Int, Int)

  implicit class JavaRegionWrapper(region: Region)(implicit size: (Int, Int))  {

    /**
     * converts a region to a java graphical region
     */
    def toJRegion: JRegion = region match {
      case Basic(s) => s.toJRegion
      case Translate(t, r) =>
        val x = cmToPixel(t._1)
        val y = -cmToPixel(t._2)
        val transform = AffineTransform.getTranslateInstance(x, y)
        new JRegion(transform.createTransformedShape(r.toJRegion))
      case Or(r1, r2) =>
        val jr = r1.toJRegion
        jr.add(r2.toJRegion)
        jr
      case And(r1, r2) =>
        val jr = r1.toJRegion
        jr.intersect(r2.toJRegion)
        jr
      case Subtract(r1, r2) =>
        val jr = r1.toJRegion
        jr.subtract(r2.toJRegion)
        jr
    }

    def draw: Draw[Graphics2D] = Drawing(canvas => canvas.fill(region.toJRegion))

  }




  implicit class JavaShapeWrapper(shape: PlainShape)(implicit size: (Int, Int)) {

    def toJRegion:JRegion = shape match {
      case Polygon(points) =>
        val transformedPts = points.map{ point =>
          val (x,y) = point
          (transformX(cmToPixel(x)), transformY(cmToPixel(y)) )
        }
        val (xCoord, yCoord) = transformedPts.unzip
        new JRegion(new JPolygon(xCoord.toArray, yCoord.toArray, points.size))
      case r @ Rectangle(_,_) => PlainShape.getPolygon(r).toJRegion
      case Circle(radius) =>
        val r = cmToPixel(radius)
        new JRegion(new Ellipse2D.Double(transformX(-r) , transformY(r), 2*r, 2*r))
    }

    private def transformX(x: Int): Int = size._1/2 + x
    private def transformY(y: Int): Int = size._2/2 - y
  }


  private def cmToPixel(cm: Double): Int = (cm * 37.8).toInt

}
