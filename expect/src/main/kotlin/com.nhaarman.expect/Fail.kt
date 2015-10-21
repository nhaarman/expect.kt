package com.nhaarman.expect

fun fail(reason: () -> Any = { "" }, init: Fail.() -> Unit) {
  val fail = Fail(reason.invoke())
  fail.init()
  kotlin.test.fail(fail.toString())
}

class Fail(val reason: Any) {

  var expected: Expected? = null

  fun expected(actual: Any?, init: Expected.() -> Unit = {}): Expected {
    val expected = Expected(actual)
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

class Expected(val actual: Any?) {

  var to: To? = null

  var but: But? = null

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
    return "\t" + "Expected ${render(actual)} ${to ?: ""}${but ?: ""}".trim() + "."
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
    "actual value", "an Error" -> "$value"
    is String -> "\"$value\""
    is Double, is Float, is Long, is Int, is Float, is Byte -> "$value"
    is Range<*> -> "[${value.start} .. ${value.end}]"
    else -> "\n\t\t$value\n "
  }
}
