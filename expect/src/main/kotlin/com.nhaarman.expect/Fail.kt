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

fun fail(reason: () -> Any = { "" }, init: Fail.() -> Unit = {}) {
    val fail = Fail(reason.invoke())
    fail.init()
    throw AssertionError(fail.toString())
}

class Fail(val reason: Any) {

    var expected: Expected? = null

    fun expected(actualValue: Any?, init: Expected.() -> Unit = {}): Expected {
        val expected = Expected(actualValue)
        expected.init()
        this.expected = expected
        return expected
    }

    override fun toString(): String {
        var result = reason.toString()

        result += expected?.let { expected ->
            "\n\n$expected"
        } ?: ""

        result += "\n"

        return result
    }
}

class Expected(val actualValue: Any?) {

    var to: To? = null

    var but: But? = null

    fun to(description: String) {
        this.to = To(description, null)
    }

    fun to(description: String, expected: Any) {
        this.to = To(description, expected)
    }

    fun to(description: String, expected: () -> Any) {
        this.to = To(description, expected.invoke())
    }

    fun but(description: String, expected: Any? = null) {
        this.but = But(description, expected)
    }

    fun but(description: String, expected: () -> Any) {
        this.but = But(description, expected)
    }

    override fun toString(): String {
        return "\t" + "Expected ${render(actualValue)} ${to ?: ""}${but ?: ""}".trim() + "."
    }
}

class To(val description: Any, val value: Any?) {

    override fun toString(): String {
        return "to $description ${render(value)}"
    }
}

class But(val description: Any, val value: Any?) {

    override fun toString(): String {
        return ", but $description ${render(value)}"
    }
}

private fun render(value: Any?): String {
    return when (value) {
        null -> ""
        "null" -> "null"
        "actual value", "an Error" -> "$value"
        is String -> "\"$value\""
        is Double, is Float, is Long, is Int, is Float, is Byte, is Boolean -> "$value"
        is ClosedRange<*> -> "[${value.start} .. ${value.endInclusive}]"
        else -> "\n\t\t$value\n "
    }
}
