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
