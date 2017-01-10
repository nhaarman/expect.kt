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

class MatcherTest {

    @Test
    fun example_toBeTheSameAs() {
        val a = Any()

        expect(a).toBeTheSameAs(a)
    }

    @Test
    fun example_toBe() {
        val actual = Data(1)
        val expected = Data(1)

        expect(actual).toBe(expected)
    }

    @Test
    fun example_toBeNull() {
        val actual: Int? = null

        expect(actual).toBeNull()
    }

    @Test
    fun toBeTheSameAs_withEqualReferences_accepts() {
        /* Given */
        val a = Any()
        val matcher = Matcher(a)

        /* When */
        matcher.toBeTheSameAs(a)

        /* Then */
        awesome()
    }

    @Test
    fun toBeTheSameAs_withUnequalReferences_fails() {
        /* Given */
        val actual = Data(1)
        val expected = Data(1)
        val matcher = Matcher(actual)

        /* Then */
        expectErrorWithMessage("to be") when_ {

            matcher.toBeTheSameAs(expected)
        }
    }

    @Test
    fun toNotBeTheSameAs_withEqualReferences_fails() {
        /* Given */
        val a = Any()
        val matcher = Matcher(a)

        /* Then */
        expectErrorWithMessage("not to be") on {
            matcher.toNotBeTheSameAs(a)
        }
    }

    @Test
    fun toNotBeTheSameAs_withUnequalReferences_accepts() {
        /* Given */
        val actual = Data(1)
        val expected = Data(1)
        val matcher = Matcher(actual)

        /* Then */
        matcher.toNotBeTheSameAs(expected)

        /* Then */
        awesome()
    }

    @Test
    fun toBe_withEqualReferences_areAccepted() {
        /* Given */
        val data = Data(1)
        val matcher = Matcher(data)

        /* When */
        matcher.toBe(data)

        /* Then */
        awesome()
    }

    @Test
    fun toBe_withEqualItems_areAccepted() {
        /* Given */
        val first = Data(1)
        val second = Data(1)
        val matcher = Matcher(first)

        /* When */
        matcher.toBe(second)

        /* Then */
        awesome()
    }

    @Test
    fun toBe_withSubtype_isNotAccepted() {
        /* Given */
        val first = Data(1)
        val second = Subdata(1)
        val matcher = Matcher(first)

        /* Then */
        expectErrorWithMessage("to be") on {
            matcher.toBe(second)
        }
    }

    @Test
    fun toBe_withUnequalItems_isNotAccepted() {
        /* Given */
        val first = 1
        val second = 2
        val matcher = Matcher(first)

        /* Then */
        expectErrorWithMessage("to be") on {
            matcher.toBe(second)
        }
    }

    @Test
    fun toBe_withNullValue_isNotAccepted() {
        /* Given */
        val matcher = Matcher<Int>(null)

        /* Then */
        expectErrorWithMessage("to be").when_ {
            matcher.toBe(1)
        }
    }

    @Test
    fun toBeNull_withNullValue_accepts() {
        /* Given */
        val matcher = Matcher<Int>(null)

        /* When */
        matcher.toBeNull()

        /* Then */
        awesome()
    }

    @Test
    fun toBeNull_withNonNullValue_fails() {
        /* Given */
        val matcher = Matcher(3)

        /* Then */
        expectErrorWithMessage("Expected 3 to be null.") on {

            matcher.toBeNull()
        }
    }

    @Test
    fun toNotBeNull_withNullValue_fails() {
        /* Given */
        val matcher = Matcher<Int>(null)

        /* When */
        expectErrorWithMessage("to not be null") on {
            matcher.toNotBeNull()
        }

        /* Then */
        awesome()
    }

    @Test
    fun toNotBeNull_withNonNullValue_accepts() {
        /* Given */
        val matcher = Matcher(3)

        /* When */
        matcher.toNotBeNull()

        /* Then */
        awesome()
    }

    @Test
    fun toBeInstanceOf_withSameClass_accepts() {
        /* Given */
        val matcher = Matcher(3)

        /* When */
        matcher.toBeInstanceOf<Int>()

        /* Then */
        awesome()
    }

    @Test
    fun toBeInstanceOf_withSubclass_accepts() {
        /* Given */
        val matcher = Matcher(Subdata(4))

        /* When */
        matcher.toBeInstanceOf<Data>()

        /* Then */
        awesome()
    }

    @Test
    fun toBeInstanceOf_notAnInstanceOf_fails() {
        /* Given */
        val matcher = Matcher(Data(4))

        /* Then */
        expectErrorWithMessage("to be an instance of") on {
            /* When */
            matcher.toBeInstanceOf<Subdata>()
        }
    }

    open class Data(val value: Int) {

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            } else if (other != null && this.javaClass == other.javaClass) {
                val data = other as Data
                return this.value == data.value
            } else {
                return false
            }
        }

        override fun hashCode(): Int {
            return value
        }

        override fun toString() = "Data(value=$value)"
    }

    class Subdata(value: Int) : Data(value) {
        override fun toString() = "Subdata(value=$value)"
    }
}
