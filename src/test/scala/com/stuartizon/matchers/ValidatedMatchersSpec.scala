package com.stuartizon.matchers

import cats.data.Validated
import cats.data.Validated._
import org.specs2.mutable.Specification

class ValidatedMatchersSpec extends Specification with ValidatedMatchers {
  def validate(success: Boolean): Validated[String, Int] =
    if (success) valid(123) else invalid("Incorrect value")

  "ValidationMatchers" should {
    "beValid for valid values" in {
      validate(true) must beValid
    }

    "not beValid for invalid values" in {
      validate(false) must not(beValid)
    }

    "not beInvalid for valid values" in {
      validate(true) must not(beInvalid)
    }

    "beInvalid for invalid values" in {
      validate(false) must beInvalid
    }

    "beValid with value for valid values" in {
      validate(true) must beValid(123)
    }

    "beInvalid with value for invalid values" in {
      validate(false) must beInvalid("Incorrect value")
    }

    "beValid with nested function for valid values" in {
      validate(true) must beValid {
        beGreaterThan(100)
      }
    }

    "beInvalid with nested function for invalid values" in {
      validate(false) must beInvalid {
        startWith("Inc")
      }
    }
  }
}