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

import org.junit.Test

class StringMatcherTest {

    @Test
    fun example_toContain() {
        val actual = "test"
        val expected = "es"

        expect(actual).toContain(expected)
    }

    @Test
    fun toBeEqualTo_withEqualStrings_accepts() {
        /* Given */
        val actual = "test"
        val expected = "test"
        val matcher = StringMatcher(actual)

        /* When */
        matcher.toBe(expected)

        /* Then */
        awesome()
    }

    @Test
    fun toBeEqualTo_withUnequalStrings_fails() {
        /* Given */
        val actual = "test"
        val expected = "nope"
        val matcher = StringMatcher(actual)

        /* Then */
        expectErrorWithMessage("but was").when_ {

            matcher.toBe(expected)
        }
    }

    @Test
    fun toContain_withEmptySubstring_accepts() {
        /* Given */
        val actual = "test"
        val expected = ""
        val matcher = StringMatcher(actual)

        /* When */
        matcher.toContain(expected)

        /* Then */
        awesome()
    }

    @Test
    fun toContain_withProperSubstring_accepts() {
        /* Given */
        val actual = "test"
        val expected = "es"
        val matcher = StringMatcher(actual)

        /* When */
        matcher.toContain(expected)

        /* Then */
        awesome()
    }

    @Test
    fun toContain_withEqualString_accepts() {
        /* Given */
        val actual = "test"
        val expected = "test"
        val matcher = StringMatcher(actual)

        /* When */
        matcher.toContain(expected)

        /* Then */
        awesome()
    }

    @Test
    fun toContain_withNonSubstring_fails() {
        /* Given */
        val actual = "test"
        val expected = "nope"
        val matcher = StringMatcher(actual)

        /* Then */
        expectErrorWithMessage("to contain") when_ {

            matcher.toContain(expected)
        }
    }

    @Test
    fun toContain_withNullValue_fails() {
        /* Given */
        val expected = "nope"
        val matcher = StringMatcher(null)

        /* Then */
        expectErrorWithMessage("but the actual value was null") when_ {

            matcher.toContain(expected)
        }
    }
}

