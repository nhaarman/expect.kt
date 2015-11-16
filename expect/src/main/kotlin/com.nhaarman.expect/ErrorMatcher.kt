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

