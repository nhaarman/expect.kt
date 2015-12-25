/*
 * Copyright 2016 Niek Haarman
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

    fun toBeIn(expected: ClosedRange<T>, reason: () -> Any = { "" }) {
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
