package com.nhaarman.expect

import kotlin.test.fail

fun expect(actual: Int?): IntMatcher {
  return IntMatcher(actual)
}

class IntMatcher(actual: Int?) : Matcher<Int>(actual) {

  fun toBeSmallerThan(expected: Int, reason: () -> Any = { "" }) {
    if (actual == null) {
      fail("${reason.invoke()}\n\tExpected actual value to be smaller than $expected, but actual value was null.")
      return
    }

    if (actual >= expected ) {
      fail("${reason.invoke()}\n\tExpected $actual to be smaller than $expected.")
    }
  }

  fun toBeGreaterThan(expected: Int, reason: () -> Any = { "" }) {
    if (actual == null) {
      fail("${reason.invoke()}\n\tExpected actual value to be greater than $expected, but actual value was null.")
      return
    }

    if (actual <= expected ) {
      fail("${reason.invoke()}\n\tExpected $actual to be greater than $expected.")
    }
  }
}
