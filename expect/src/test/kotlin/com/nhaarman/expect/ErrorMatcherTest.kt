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

class ErrorMatcherTest {

    @Test
    fun example_expectErrorWithMessage() {
        expectErrorWithMessage("test").when_ {
            throw Error("test")
        }
    }

    @Test
    fun on_withAnError_accepts() {
        /* Given */
        val matcher = ErrorMatcher("test")

        /* When */
        matcher.on {
            throw Error("test")
        }

        /* Then */
        awesome()
    }

    @Test
    fun on_withoutAnError_fails() {
        /* Given */
        val matcher = ErrorMatcher("test")

        /* When */
        try {
            matcher.on {}
        } catch(e: Error) {
            awesome()
            return
        }

        fail({ "Expected an error to be thrown" })
    }

    @Test
    fun on_withWrongErrorMessage_fails() {
        /* Given */
        val matcher = ErrorMatcher("test")

        /* When */
        try {
            matcher.on {
                throw Error("nope")
            }
            fail({ "Expected an error to be thrown" })
        } catch(e: Error) {
            if (e.message?.contains("but the following Error was thrown") == true) {
                return
            }

            fail({ "Expected error with wrong error message to be thrown" })
        }
    }

    @Test
    fun when_withAnError_accepts() {
        /* Given */
        val matcher = ErrorMatcher("test")

        /* When */
        matcher.when_ {
            throw Error("test")
        }

        /* Then */
        awesome()
    }

    @Test
    fun when_withoutAnError_fails() {
        /* Given */
        val matcher = ErrorMatcher("test")

        /* When */
        try {
            matcher.when_ {}
            fail({ "Expected an error to be thrown" })
        } catch(e: Error) {
            awesome()
        }
    }

    @Test
    fun when_withWrongErrorMessage_fails() {
        /* Given */
        val matcher = ErrorMatcher("test")

        /* When */
        try {
            matcher.when_ {
                throw Error("nope")
            }
            fail({ "Expected an error to be thrown" })
        } catch(e: Error) {
            if (e.message?.contains("but the following Error was thrown") == true) {
                return
            }

            fail({ "Expected error with wrong error message to be thrown" })
        }
    }
}
