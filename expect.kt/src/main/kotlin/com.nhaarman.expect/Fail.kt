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


inline fun fail(reason: String): Nothing = throw AssertionError(reason)

inline fun fail(reason: String, message: () -> Any?): Nothing {
    var m = reason
    message()?.let {
        m = "$reason\n$it"
    }
    throw AssertionError(m)
}

inline fun fail(expected: Any?, actual: Any?, message: () -> Any?): Nothing {
    throw AssertionError("Expected: $actual but was: $expected\n\t${message()}")
}