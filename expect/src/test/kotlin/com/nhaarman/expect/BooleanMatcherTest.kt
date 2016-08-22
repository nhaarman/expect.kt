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

class BooleanMatcherTest {

    @Test
    fun example_toHold() {
        expect(true).toHold()
    }

    @Test
    fun example_notToHold() {
        expect(false).notToHold()
    }

    @Test
    fun toHold_withTrueValue_accepts() {
        /* Given */
        val actual = true
        val matcher = BooleanMatcher(actual)

        /* When */
        matcher.toHold()

        /* Then */
        awesome()
    }

    @Test
    fun toHold_withFalseValue_fails() {
        /* Given */
        val actual = false
        val matcher = BooleanMatcher(actual)

        /* Then */
        expectErrorWithMessage("Expected false to be true.") on{

            matcher.toHold()
        }
    }

    @Test
    fun toHold_withNullValue_fails() {
        /* Given */
        val matcher = BooleanMatcher(null)

        /* Then */
        expectErrorWithMessage("Expected value to be true, but the actual value was null.") on{

            matcher.toHold()
        }
    }

    @Test
    fun notToHold_withFalseValue_accepts() {
        /* Given */
        val actual = false
        val matcher = BooleanMatcher(actual)

        /* When */
        matcher.notToHold()

        /* Then */
        awesome()
    }

    @Test
    fun notToHold_withTrueValue_fails() {
        /* Given */
        val actual = true
        val matcher = BooleanMatcher(actual)

        /* Then */
        expectErrorWithMessage("Expected true to be false.") on{

            matcher.notToHold()
        }
    }

    @Test
    fun notToHold_withNullValue_fails() {
        /* Given */
        val matcher = BooleanMatcher(null)

        /* Then */
        expectErrorWithMessage("Expected value to be false, but the actual value was null.") on{

            matcher.notToHold()
        }
    }
}
