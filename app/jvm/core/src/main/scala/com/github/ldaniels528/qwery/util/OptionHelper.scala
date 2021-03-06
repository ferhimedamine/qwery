package com.github.ldaniels528.qwery.util

import scala.language.implicitConversions
import scala.util.{Failure, Success, Try}

/**
  * Option Helper Utility Class
  * @author Lawrence Daniels <lawrence.daniels@gmail.com>
  */
object OptionHelper {

  object Risky {

    implicit def value2Option[T](value: T): Option[T] = Option(value)

  }

  /**
    * Facilitates option chaining
    */
  implicit class OptionalExtensions[T](val opA: Option[T]) extends AnyVal {

    @inline
    def ??(opB: => Option[T]): Option[T] = if (opA.isDefined) opA else opB

    @inline
    def orDie(message: String): T = opA.getOrElse(throw new IllegalStateException(message))

  }

  /**
    * Optional double extensions
    */
  implicit class OptionalDoubleExtensions(val opA: Option[Double]) extends AnyVal {

    @inline
    def orZero: Double = opA.getOrElse(0d)

  }

  /**
    * Optional integer extensions
    */
  implicit class OptionalIntExtensions(val opA: Option[Int]) extends AnyVal {

    @inline
    def orZero: Int = opA.getOrElse(0)

  }

  /**
    * Optional integer extensions
    */
  implicit class OptionalLongExtensions(val opA: Option[Long]) extends AnyVal {

    @inline
    def orZero: Long = opA.getOrElse(0L)

  }

  /**
    * Converts a Success outcome into an Option of the outcome and Failure(e) to None
    * @param outcome the given Try-monad, which represents the outcome
    * @tparam T the parameter type
    */
  implicit class TryToOptionExtension[T](val outcome: Try[T]) extends AnyVal {

    def toOption: Option[T] = outcome match {
      case Success(v) => Option(v)
      case Failure(e) => None
    }

  }

}
