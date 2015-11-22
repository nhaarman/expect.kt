package com.nhaarman.expect

fun expect(actual: Boolean?): BooleanMatcher {
    return BooleanMatcher(actual)
}

class BooleanMatcher(actual: Boolean?) : Matcher<Boolean>(actual) {

    fun toHold(reason: () -> Any = { "" }) {
        if (actual == null) {
            fail(reason) {
                expected("actual value") {
                    to("be") { true }
                    but("the actual value was null")
                }
            }

            return
        }

        if (actual != true ) {
            fail(reason) {
                expected(actual) { to("be") { true } }
            }
        }
    }

    fun notToHold(reason: () -> Any = { "" }) {
        if (actual == null) {
            fail(reason) {
                expected("actual value") {
                    to("be") { false }
                    but("the actual value was null")
                }
            }

            return
        }

        if (actual != false ) {
            fail(reason) {
                expected(actual) { to("be") { false } }
            }
        }
    }

}
