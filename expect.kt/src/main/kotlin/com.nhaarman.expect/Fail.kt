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


fun fail(reason: String): Nothing = throw AssertionError(reason)

fun fail(reason: String, message: (() -> Any?)? = null): Nothing {
    var m = reason
    message?.invoke()?.let {
        m = "$reason\n$it"
    }
    throw AssertionError(m)
}

fun fail(expected: Any?, actual: Any?, message: (() -> Any?)? = null): Nothing {
    val m = message?.invoke()?.let { "$it\n" } ?: ""

    throw AssertionError("${m}Expected: $expected but was: $actual\n")
}