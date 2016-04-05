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

class NumberMatcherTest {

    @Test
    fun example_double_toBeEqualTo() {
        expect(1.0).toBe(1.0)
    }

    @Test
    fun example_double_toBeSmallerThan() {
        expect(1.0).toBeSmallerThan(2.0)
    }

    @Test
    fun example_double_toBeGreaterThan() {
        expect(1.0).toBeGreaterThan(0.0)
    }

    @Test
    fun example_double_toBeIn() {
        expect(0.5).toBeIn(0.0..1.0)
    }

    @Test
    fun example_float_toBeEqualTo() {
        expect(1.0f).toBe(1.0f)
    }

    @Test
    fun example_float_toBeSmallerThan() {
        expect(1.0f).toBeSmallerThan(2.0f)
    }

    @Test
    fun example_float_toBeGreaterThan() {
        expect(1.0f).toBeGreaterThan(0.0f)
    }

    @Test
    fun example_long_toBeEqualTo() {
        expect(1L).toBe(1L)
    }

    @Test
    fun example_long_toBeSmallerThan() {
        expect(1L).toBeSmallerThan(2L)
    }

    @Test
    fun example_long_toBeGreaterThan() {
        expect(1L).toBeGreaterThan(0L)
    }

    @Test
    fun example_int_toBeEqualTo() {
        expect(1).toBe(1)
    }

    @Test
    fun example_int_toBeSmallerThan() {
        expect(1).toBeSmallerThan(2)
    }

    @Test
    fun example_int_toBeGreaterThan() {
        expect(1).toBeGreaterThan(0)
    }

    @Test
    fun example_short_toBeEqualTo() {
        val actual: Short = 1
        val expected: Short = 1
        expect(actual).toBe(expected)
    }

    @Test
    fun example_short_toBeSmallerThan() {
        val actual: Short = 1
        val expected: Short = 2
        expect(actual).toBeSmallerThan(expected)
    }

    @Test
    fun example_short_toBeGreaterThan() {
        val actual: Short = 1
        val expected: Short = 0
        expect(actual).toBeGreaterThan(expected)
    }

    @Test
    fun example_byte_toBeEqualTo() {
        val actual: Byte = 1
        val expected: Byte = 1
        expect(actual).toBe(expected)
    }

    @Test
    fun example_byte_toBeSmallerThan() {
        val actual: Byte = 1
        val expected: Byte = 2
        expect(actual).toBeSmallerThan(expected)
    }

    @Test
    fun example_byte_toBeGreaterThan() {
        val actual: Byte = 1
        val expected: Byte = 0
        expect(actual).toBeGreaterThan(expected)
    }

    @Test
    fun toBeEqual_withEqualValues_accepts() {
        /* Given */
        val matcher = NumberMatcher(1.0)

        /* When */
        matcher.toBe(1.0)

        /* Then */
        awesome()
    }

    @Test
    fun toBeEqual_withUnEqualValues_fails() {
        /* Given */
        val matcher = NumberMatcher(1.0)

        /* Then */
        expectErrorWithMessage("to be equal to").on {

            matcher.toBe(2.0)
        }
    }

    @Test
    fun toBeSmallerThan_withSmallerActualValue_accepts() {
        /* Given */
        val actual = 1.0
        val expected = 2.0
        val matcher = NumberMatcher(actual)

        /* When */
        matcher.toBeSmallerThan(expected)

        /* Then */
        awesome()
    }

    @Test
    fun toBeSmallerThan_withEqualActualValue_fails() {
        /* Given */
        val actual = 1.0
        val expected = 1.0
        val matcher = NumberMatcher(actual)

        /* Then */
        expectErrorWithMessage("Expected 1.0 to be smaller than 1.0.") on{

            matcher.toBeSmallerThan(expected)
        }
    }

    @Test
    fun toBeSmallerThan_withGreaterActualValue_fails() {
        /* Given */
        val actual = 2.0
        val expected = 1.0
        val matcher = NumberMatcher(actual)

        /* Then */
        expectErrorWithMessage("Expected 2.0 to be smaller than 1.0.") on{

            matcher.toBeSmallerThan(expected)
        }
    }

    @Test
    fun toBeSmallerThan_withNullActualValue_fails() {
        /* Given */
        val expected = 1.0
        val matcher = NumberMatcher<Double>(null)

        /* Then */
        expectErrorWithMessage("Expected actual value to be smaller than 1.0, but the actual value was null.") on{

            matcher.toBeSmallerThan(expected)
        }
    }

    @Test
    fun toBeGreaterThan_withGreaterActualValue_accepts() {
        /* Given */
        val actual = 2.0
        val expected = 1.0
        val matcher = NumberMatcher(actual)

        /* When */
        matcher.toBeGreaterThan(expected)

        /* Then */
        awesome()
    }

    @Test
    fun toBeGreaterThan_withEqualActualValue_fails() {
        /* Given */
        val actual = 1.0
        val expected = 1.0
        val matcher = NumberMatcher(actual)

        /* Then */
        expectErrorWithMessage("Expected 1.0 to be greater than 1.0.") on{

            matcher.toBeGreaterThan(expected)
        }
    }

    @Test
    fun toBeGreaterThan_withSmallerActualValue_fails() {
        /* Given */
        val actual = 1.0
        val expected = 2.0
        val matcher = NumberMatcher(actual)

        /* Then */
        expectErrorWithMessage("Expected 1.0 to be greater than 2.0.") on{

            matcher.toBeGreaterThan(expected)
        }
    }

    @Test
    fun toBeGreaterThan_withNullActualValue_fails() {
        /* Given */
        val expected = 1.0
        val matcher = NumberMatcher<Double>(null)

        /* Then */
        expectErrorWithMessage("Expected actual value to be greater than 1.0, but the actual value was null.") on{

            matcher.toBeGreaterThan(expected)
        }
    }

    @Test
    fun toBeIn_withInRangeValue_accepts() {
        /* Given */
        val actual = 1
        val expected = 0..2
        val matcher = NumberMatcher(actual)

        /* When */
        matcher.toBeIn(expected)

        /* Then */
        awesome()
    }

    @Test
    fun toBeIn_withOutRangeValue_accepts() {
        /* Given */
        val actual = 4
        val expected = 0..2
        val matcher = NumberMatcher(actual)

        /* When */
        expectErrorWithMessage("Expected 4 to be in [0 .. 2].") on {

            matcher.toBeIn(expected)
        }

        /* Then */
        awesome()
    }
}
