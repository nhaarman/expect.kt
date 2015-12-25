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

fun <T> expect(actual: List<T>?): ListMatcher<T> {
    return ListMatcher(actual)
}

class ListMatcher<T>(actual: List<T>?) : Matcher<List<T>>(actual) {

    fun toBeEmpty(reason: () -> Any = { "" }) {
        if (actual == null) {
            fail(reason) {
                expected("actual value") {
                    to("be empty")
                    but("the actual value was null")
                }
            }

            return
        }

        if (actual.isNotEmpty()) {
            fail(reason) {
                expected(actual) {
                    to("be empty")
                    but("the size was ${actual.size}")
                }
            }
        }
    }

    fun toHaveSize(size: Int, reason: () -> Any = { "" }) {
        if (actual == null) {
            fail(reason) {
                expected("actual value") {
                    to("be have size $size")
                    but("the actual value was null")
                }
            }

            return
        }

        if (actual.size != size) {
            fail(reason) {
                expected(actual) {
                    to("be have size $size}")
                    but("the size was ${actual.size}")
                }
            }
        }
    }

    fun toContain(expected: T, reason: () -> Any = { "" }) {
        if (actual == null) {
            fail(reason) {
                expected("actual value") {
                    to("contain", expected.toString())
                    but("the actual value was null")
                }
            }

            return
        }

        if (!actual.contains(expected)) {
            fail(reason) {
                expected(actual) { to("contain") { expected.toString() } }
            }
        }
    }
}
