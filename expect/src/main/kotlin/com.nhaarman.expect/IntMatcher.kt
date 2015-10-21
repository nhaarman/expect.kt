package com.nhaarman.expect

fun expect(actual: Int?): IntMatcher {
  return IntMatcher(actual)
}

class IntMatcher(actual: Int?) : Matcher<Int>(actual) {

  fun toBeSmallerThan(expected: Int, reason: () -> Any = { "" }) {
    if (actual == null) {
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

  fun toBeGreaterThan(expected: Int, reason: () -> Any = { "" }) {
    if (actual == null) {
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
