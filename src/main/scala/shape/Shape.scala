package shape

import shape.Shape.{Radius, Side, Vertex}

/**
 * Created by rakesh.h on 13/09/15.
 */
sealed trait Shape
case class Circle(radius: Radius) extends Shape
case class Rectangle(width: Side, height: Side) extends Shape
case class Polygon(vertices: Seq[Vertex]) extends Shape

object Shape {
  type Radius = Double
  type Side = Double
  type Vertex = (Double, Double)
  type Angle = Double

  def rotate(shape: Shape, angle: Angle): Shape = shape match {
    // create a new polygon with all the points rotated through map
    case Polygon(points) => Polygon(points.map(x => rotate(x, angle)))
    //get rectangle in terms of polygon and rotate it
    case r @ Rectangle(_,_)  => rotate(getPolygon(r), angle)
    case c @ Circle(_) => c  //rotation of circle which is centred around origin,
                             //is same circle
  }

  /**
   * rotates a vertex around origin.
   */
  def rotate(vertex: Vertex, angle: Angle): Vertex = {
    val (x,y) = vertex
    val c = Math.cos(Math.toRadians(angle))
    val s = Math.sin(Math.toRadians(angle))
    val newX = x * c - y * s
    val newY = x * s + y * c
    (newX, newY)
  }

  def getPolygon(rectangle: Rectangle): Polygon = rectangle match {
    case Rectangle(w, h) => Polygon((-w/2, h/2) :: (w/2, h/2) :: (w/2, -h/2) :: (-w/2, -h/2) :: Nil)
  }

}
