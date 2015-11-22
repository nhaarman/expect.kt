package com.nhaarman.expect

fun expect(actual: String?): StringMatcher {
    return StringMatcher(actual)
}

class StringMatcher(actual: String?) : Matcher<String>(actual) {

    fun toContain(expected: String, reason: () -> Any = { "" }) {
        if (actual == null) {
            fail(reason) {
                expected("actual value") {
                    to("contain", expected)
                    but("the actual value was null")
                }
            }

            return
        }

        if (!actual.contains(expected)) {
            fail(reason) {
                expected(actual) { to("contain") { expected } }
            }
        }
    }
}
