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

import io.reactivex.observers.TestObserver

fun <T> expect(actual: TestObserver<T>) = TestObserverMatcher(actual)

class TestObserverMatcher<T>(override val actual: TestObserver<T>) : Matcher<TestObserver<T>>(actual) {

    fun toHaveNoValues(message: (() -> Any?)? = null) {
        if (actual.valueCount() != 0) {
            fail("Expected no values, but the following values were observed:\n\n\t${actual.values()}", message)
        }
    }

    fun toHaveValueCount(expected: Int, message: (() -> Any?)? = null) {
        if (actual.valueCount() != expected) {
            fail("Expected $expected observations, but ${actual.valueCount()} values were observed:\n\n\t${actual.values()}", message)
        }
    }

    fun toHaveValues(vararg expected: T, message: (() -> Any?)? = null) {
        expect(actual).toHaveValueCount(expected.size, message)
        expect(actual.values()).toBe(expected.toList())
    }

    fun toBeCompleted(message: (() -> Any?)? = null) {
        try {
            actual.assertComplete()
        } catch (e: AssertionError) {
            fail(e.message ?: "", message)
        }
    }

    fun toHaveError(expected: Throwable) {
        actual.assertError(expected)
    }
}

val <T> TestObserver<T>.lastValue: T
    get() = values().last()