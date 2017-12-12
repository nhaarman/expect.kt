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

fun <T : Any> expect(actual: T?): Matcher<T> {
    return Matcher(actual)
}

open class Matcher<T : Any>(open val actual: T?) {

    fun toBeTheSameAs(expected: T, message: (() -> Any?)? = null) {
        if (actual !== expected) {
            fail(expected = expected, actual = actual, message = message)
        }
    }

    fun toNotBeTheSameAs(expected: T, message: (() -> Any?)? = null) {
        if (actual === expected) {
            fail("Expected $actual not to be $expected.", message)
        }
    }

    open fun toBe(expected: T, message: (() -> Any?)? = null) {
        if (actual != expected) {
            fail(expected = expected, actual = actual, message = message)
        }
    }

    fun toBeNull(message: (() -> Any?)? = null) {
        if (actual != null) {
            fail("Expected $actual to be null.", message)
        }
    }

    fun toNotBeNull(message: (() -> Any?)? = null) {
        if (actual == null) {
            fail("Expected $actual to not be null.", message)
        }
    }

    inline fun <reified R : Any> toBeInstanceOf(): Unit = toBeInstanceOf<R>({})

    inline fun <reified R : Any> toBeInstanceOf(capture: (R) -> Any?) {
        if (actual !is R) {
            fail("Expected $actual to be an instance of ${R::class.java.canonicalName}.")
        }

        capture(actual as R)
    }
}
