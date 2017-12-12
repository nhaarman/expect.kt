/*
 * Copyright 2017 Niek Haarman
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

class ListMatcher<T>(override val actual: List<T>?) : Matcher<List<T>>(actual) {

    fun toBeEmpty(message: () -> Any = { "" }) {
        if (actual == null) {
            fail("Expected value to be empty, but the actual value was null.", message)
        }

        if (actual.isNotEmpty()) {
            fail("Expected $actual to be empty.", message)
        }
    }

    fun toHaveSize(size: Int, message: () -> Any = { "" }) {
        if (actual == null) {
            fail("Expected value to have size $size, but the actual value was null.", message)
        }

        if (actual.size != size) {
            fail("Expected $actual to have size $size, but the actual size was ${actual.size}.", message)
        }
    }

    fun toContain(expected: T, message: () -> Any = { "" }) {
        if (actual == null) {
            fail("Expected value to contain $expected, but the actual value was null.", message)
        }

        if (!actual.contains(expected)) {
            fail("Expected $actual to contain $expected", message)
        }
    }
}
