package com.nhaarman.expect

fun <T : Number> expect(actual: T?): NumberMatcher<T> where T : Comparable<T> {
  return NumberMatcher(actual)
}

class NumberMatcher<T : Number>(actual: T?) : Matcher<T>(actual) where T : Comparable<T> {

  fun toBeSmallerThan(expected: T, reason: () -> Any = { "" }) {
    if (actual === null) {
      fail(reason) {
        expected("actual value") {
          to ("be smaller than") { expected }
          but("the actual value was null")
        }
      }
      return
    }

    if (actual >= expected ) {
      fail(reason) {
        expected(actual) { to("be smaller than") { expected } }
      }
    }
  }

  fun toBeGreaterThan(expected: T, reason: () -> Any = { "" }) {
    if (actual === null) {
      fail(reason) {
        expected("actual value") {
          to ("be greater than") { expected }
          but("the actual value was null")
        }
      }
      return
    }

    if (actual <= expected ) {
      fail(reason) {
        expected(actual) { to("be greater than") { expected } }
      }
    }
  }
}
