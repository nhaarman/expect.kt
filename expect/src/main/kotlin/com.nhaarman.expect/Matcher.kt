package com.nhaarman.expect

import kotlin.test.fail

fun <T> expect(actual: T?): Matcher<T> {
  return Matcher(actual)
}

open class Matcher<T>(val actual: T?) {

  fun toBe(expected: T, reason: () -> Any = { "" }) {
    if (actual !== expected) {
      fail("${reason.invoke()}\n\n\tExpected\n\t\t$actual\n\tto be:\n\t\t$expected.\n")
    }
  }

  open fun toBeEqualTo(expected: T, reason: () -> Any = { "" }) {
    if (actual != expected ) {
      fail("${reason.invoke()}\n\n\tExpected\n\t\t$actual\n\tto be equal to:\n\t\t$expected.\n")
    }
  }
}
