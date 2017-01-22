package region

import region.Region._
import shape.Shape

/**
 * Created by rakesh.h on 14/09/15.
 */
sealed trait Region

//a wrapper over simple shape
case class Basic(shape: Shape) extends Region

//addition of two shapes
case class Or(r1: Region, r2: Region) extends Region

//intersection of two shapes
case class And(r1: Region, r2: Region) extends Region

//it subtracts r1 and r2
case class Subtract(r1: Region, r2: Region) extends Region

//it moves the region r by an amount point
case class Translate(point: Point, r: Region) extends Region

object Region {
  type Point = (Double, Double)
  type Angle = Double

  def rotate(r: Region, angle: Angle): Region = r match {
    case Basic(s) => Basic(Shape.rotate(s, angle))
    case Translate(t, r) => Translate(Shape.rotate(t, angle), rotate(r, angle))
    case And(r1, r2) => And(rotate(r1, angle), rotate(r2, angle))
    case Or(r1, r2) => Or(rotate(r1, angle), rotate(r2, angle))
    case Subtract(r1, r2) => Subtract(rotate(r1, angle), rotate(r2, angle))
  }

  implicit class RegionOps(self: Region) {
    def ++(r: Region): Region = Or(self, r)
    def -->(point: Point) = Translate(point, self)
    def -(r:Region): Region = Subtract(self, r)
    def &&(r: Region): Region = And(self, r)
    def ||(r: Region): Region = Or(self, r)
  }
}