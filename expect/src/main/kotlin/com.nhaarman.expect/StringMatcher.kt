package com.nhaarman.expect

import kotlin.test.fail

fun expect(actual: String?): StringMatcher {
  return StringMatcher(actual)
}

class StringMatcher(actual: String?) : Matcher<String>(actual) {

  fun toContain(expected: String, reason: () -> Any = { "" }) {
    if (actual == null) {
      fail("${reason.invoke()}\n\tExpected actual value to contain \"$expected\", but the actual value was null.")
      return
    }

    if (!actual.contains(expected)) {
      fail("${reason.invoke()}\n\tExpected \"$actual\" to contain \"$expected\".")
    }
  }
}
