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

import java.io.PrintWriter
import java.io.StringWriter

fun expectErrorWithMessage(message: String): ErrorMatcher {
    return ErrorMatcher(message)
}

class ErrorMatcher(val message: String) {

    infix fun when_(function: () -> Any?) = on(function)

    infix fun on(function: () -> Any?) {
        try {
            function.invoke()
        } catch(e: Error) {
            if (e.message?.contains(message) == true) {
                return;
            }

            var actualStackTrace = stackTraceToString(e)

            fail {
                expected("an Error") {
                    to("be thrown containing") { message }
                    but("the following Error was thrown:", actualStackTrace)
                }
            }
        }

        fail {
            expected("an Error") {
                to("be thrown containing") { message }
            }
        }
    }

    private fun stackTraceToString(t: Throwable): String {
        val sw = StringWriter();
        val pw = PrintWriter(sw);
        t.printStackTrace(pw);
        return sw.toString(); // stack trace as a string
    }
}

