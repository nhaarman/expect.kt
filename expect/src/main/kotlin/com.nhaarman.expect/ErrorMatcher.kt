package com.nhaarman.expect

import java.io.PrintWriter
import java.io.StringWriter
import kotlin.test.fail

fun expectErrorWithMessage(message: String): ErrorMatcher {
  return ErrorMatcher(message)
}

class ErrorMatcher(val message: String) {

  fun when_(function: () -> Any?) = on(function)

  fun on(function: () -> Any?) {
    try {
      function.invoke()
    } catch(e: Error) {
      if (e.getMessage()?.contains(message) == true) {
        return;
      }

      var actualStackTrace = stackTraceToString(e)

      fail("\n\n\tExpected an Error to be thrown containing\n\t\t${message}\n\n\tBut the following Error was thrown:\n\n$actualStackTrace\n")
    }

    fail("\n\n\tExpected an Error to be thrown containing:\n\t\t${message}\n")
  }

  private fun stackTraceToString(t: Throwable): String {
    val sw = StringWriter();
    val pw = PrintWriter(sw);
    t.printStackTrace(pw);
    return sw.toString(); // stack trace as a string
  }
}

