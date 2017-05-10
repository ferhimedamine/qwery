package com.github.ldaniels528.qwery.ops

/**
  * Represents a function reference
  * @author lawrence.daniels@gmail.com
  */
case class FunctionRef(name: String, args: Seq[Value]) extends Value {

  override def compare(that: Value, scope: Scope): Int = {
    evaluate(scope).map(Value.apply).map(_.compare(that, scope)) getOrElse -1
  }

  override def evaluate(scope: Scope): Option[Any] = {
    scope.lookup(this).flatMap(_.invoke(scope, args))
  }

}