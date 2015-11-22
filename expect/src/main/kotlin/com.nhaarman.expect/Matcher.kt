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

    fun toNotBe(expected: T, reason: () -> Any = { "" }) {
        if (actual === expected) {
            fail(reason) {
                expected(actual) { to("not be") { expected } }
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

    fun toBeNull(reason: () -> Any = { "" }) {
        if (actual != null) {
            fail (reason) {
                expected(actual) { to("be", "null") }
            }
        }
    }

    fun toNotBeNull(reason: () -> Any = { "" }) {
        if (actual == null) {
            fail (reason) {
                expected(actual) { to("not be", "null") }
            }
        }
    }
}
