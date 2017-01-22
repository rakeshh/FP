package animation

import animation.Animation.Time
import animation.Animation._

/**
 * Created by rakesh.h on 18/09/15.
 */
sealed trait Animation[A] {self =>

  def map[B](f : A => B): Animation[B] = self match {
    case Animate(g) => Animate(time => f(g(time)))
  }
    //flatMap(a => unit(f(a)))

  /**
   * it passes the time as it is from first animation to second one.
   */
  def flatMap[B](f: A => Animation[B]):Animation[B] = self match {
    case Animate(fa) => Animate{time =>
      val animB = f(fa(time))
      animB match {
        case Animate(fb) => fb(time)
      }
    }
  }

  def run(time: Time): A = self match {
    case Animate(f) => f(time)
  }
}

case class Animate[A](f: Time => A) extends Animation[A]

object Animation {
  def apply[A](f: Time => A): Animate[A] = Animate(f)

  type Time = Double

  def timeAnimation: Animation[Time] = Animate(identity)

  def unit[A](a: => A): Animation[A] = Animate(_ => a)

  def map[A,B](aa: Animation[A])(f: A => B): Animation[B] = aa match {
    case Animate(g) => Animate(time => f(g(time)))
  }

  def lift0[A](a: => A): Animation[A] = unit(a)

  def lift1[A,B](an: Animation[A])(f: A => B): Animation[B] = an.map(f)

  def lift2[A,B,C](aa: Animation[A])(ab: Animation[B])(f: (A, B) => C): Animation[C] =
    for {
      a <- aa
      b <- ab
    } yield f(a,b)
}
