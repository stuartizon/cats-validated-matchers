package com.stuartizon.matchers

import cats.data.Validated
import org.specs2.matcher.{OptionLikeCheckedMatcher, OptionLikeMatcher, ValueCheck}

import scala.languageFeature.reflectiveCalls

trait ValidatedMatchers {
  def beValid[T](t: ValueCheck[T]): ValidCheckedMatcher[T] = ValidCheckedMatcher(t)

  def beValid[T]: ValidMatcher[T] = new ValidMatcher[T]

  def beInvalid[T](t: ValueCheck[T]): InvalidCheckedMatcher[T] = InvalidCheckedMatcher(t)

  def beInvalid[T]: InvalidMatcher[T] = InvalidMatcher[T]()
}

case class ValidMatcher[T]() extends OptionLikeMatcher[({type l[a] = Validated[_, a]})#l, T, T]("Success", (_: Validated[Any, T]).toEither.right.toOption)

case class ValidCheckedMatcher[T](check: ValueCheck[T]) extends OptionLikeCheckedMatcher[({type l[a] = Validated[_, a]})#l, T, T]("Success", (_: Validated[Any, T]).toEither.right.toOption, check)

case class InvalidMatcher[T]() extends OptionLikeMatcher[({type l[a] = Validated[a, _]})#l, T, T]("Failure", (_: Validated[T, Any]).toEither.left.toOption)

case class InvalidCheckedMatcher[T](check: ValueCheck[T]) extends OptionLikeCheckedMatcher[({type l[a] = Validated[a, _]})#l, T, T]("Failure", (_: Validated[T, Any]).toEither.left.toOption, check)