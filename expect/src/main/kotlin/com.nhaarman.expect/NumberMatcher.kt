package com.nhaarman.expect

fun <T> expect(actual: T?): NumberMatcher<T> where T : Number, T : Comparable<T> {
    return NumberMatcher(actual)
}

class NumberMatcher<T>(actual: T?) : Matcher<T>(actual) where T : Number, T : Comparable<T> {

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

    fun toBeIn(expected: Range<T>, reason: () -> Any = { "" }) {
        if (actual === null) {
            fail(reason) {
                expected("actual value") {
                    to ("be in range") { expected }
                    but("the actual value was null")
                }
            }
            return
        }

        if (actual !in expected ) {
            fail(reason) {
                expected(actual) { to("be in") { expected } }
            }
        }
    }
}
