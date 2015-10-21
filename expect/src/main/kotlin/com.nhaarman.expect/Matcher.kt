package com.nhaarman.expect

fun <T : Any> expect(actual: T?): Matcher<T> {
  return Matcher(actual)
}

open class Matcher<T : Any>(val actual: T?) {

  fun toBe(expected: T, reason: () -> Any = { "" }) {
    if (actual !== expected) {
      fail (reason) {
        expected(actual) { to("be") { expected } }
      }
    }
  }

  open fun toBeEqualTo(expected: T, reason: () -> Any = { "" }) {
    if (actual != expected ) {
      fail (reason) {
        expected(actual) { to("be equal to", expected) }
      }
    }
  }
}
