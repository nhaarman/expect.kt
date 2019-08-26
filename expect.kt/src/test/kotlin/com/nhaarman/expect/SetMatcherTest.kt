/*
 * Copyright 2019 Uziel Sulkies
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

class SetMatcherTest {

    @Test
    fun example_toBeEmpty() {
        val actual = emptySet<String>()

        expect(actual).toBeEmpty()
    }

    @Test
    fun example_toContain() {
        val actual = setOf("one", "two")
        val expected = "one"

        expect(actual).toContain(expected)
    }

    @Test
    fun example_toHaveSize() {
        val actual = setOf("one", "two")
        val expected = 2

        expect(actual).toHaveSize(expected)
    }

    @Test
    fun toBeEmpty_withEmptySet_accepts() {
        /* Given */
        val actual = emptySet<String>()
        val matcher = SetMatcher(actual)

        /* When */
        matcher.toBeEmpty()

        /* Then */
        awesome()
    }

    @Test
    fun toBeEmpty_withNonEmptySet_fails() {
        /* Given */
        val actual = setOf("one")
        val matcher = SetMatcher(actual)

        /* Then */
        expectErrorWithMessage("to be empty") when_ {
            matcher.toBeEmpty()
        }
    }

    @Test
    fun toBeEmpty_withNullSet_fails() {
        /* Given */
        val actual: Set<String>? = null
        val matcher = SetMatcher(actual)

        /* Then */
        expectErrorWithMessage("to be empty") when_ {
            matcher.toBeEmpty()
        }
    }

    @Test
    fun toContain_withContaining_accepts() {
        /* Given */
        val actual = setOf("one", "two")
        val expected = "two"
        val matcher = SetMatcher(actual)

        /* When */
        matcher.toContain(expected)

        /* Then */
        awesome()
    }

    @Test
    fun toContain_withNotContaining_fails() {
        /* Given */
        val actual = setOf("one", "two")
        val expected = "three"
        val matcher = SetMatcher(actual)

        /* Then */
        expectErrorWithMessage("to contain") when_ {

            matcher.toContain(expected)
        }
    }

    @Test
    fun toContain_withNullValue_fails() {
        /* Given */
        val expected = "one"
        val matcher = SetMatcher<String>(null)

        /* Then */
        expectErrorWithMessage("but the actual value was null") when_ {

            matcher.toContain(expected)
        }
    }

    @Test
    fun toHaveSize_withProperSize_accepts() {
        /* Given */
        val actual = setOf("one", "two")
        val expected = 2
        val matcher = SetMatcher(actual)

        /* When */
        matcher.toHaveSize(expected)

        /* Then */
        awesome()
    }

    @Test
    fun toHaveSize_withNotProperSize_fails() {
        /* Given */
        val actual = setOf("one", "two")
        val expected = 3
        val matcher = SetMatcher(actual)

        /* Then */
        expectErrorWithMessage("have size 3") when_ {
            matcher.toHaveSize(expected)
        }
    }

    @Test
    fun toHaveSize_withNullSet_fails() {
        /* Given */
        val actual: Set<String>? = null
        val expected = 3
        val matcher = SetMatcher(actual)

        /* Then */
        expectErrorWithMessage("have size 3") when_ {
            matcher.toHaveSize(expected)
        }
    }
}

