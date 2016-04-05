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

fun <T : Any> expect(actual: T?): Matcher<T> {
    return Matcher(actual)
}

open class Matcher<T : Any>(val actual: T?) {

    fun toBeReferentially(expected: T, reason: () -> Any = { "" }) {
        if (actual !== expected) {
            fail (reason) {
                expected(actual) { to("be") { expected } }
            }
        }
    }

    fun toNotBeReferentially(expected: T, reason: () -> Any = { "" }) {
        if (actual === expected) {
            fail(reason) {
                expected(actual) { to("not be") { expected } }
            }
        }
    }

    open fun toBe(expected: T, reason: () -> Any = { "" }) {
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
