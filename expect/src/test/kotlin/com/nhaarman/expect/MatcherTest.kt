package com.nhaarman.expect

import org.junit.Test

class MatcherTest {

  @Test
  fun example_toBe() {
    val a = Any()

    expect(a).toBe(a)
  }

  @Test
  fun example_toBeEqualTo() {
    val actual = Data(1)
    val expected = Data(1)

    expect(actual).toBeEqualTo(expected)
  }

  @Test
  fun example_toBeNull() {
    val actual: Int? = null

    expect(actual).toBeNull()
  }

  @Test
  fun toBe_withEqualReferences_accepts() {
    /* Given */
    val a = Any()
    val matcher = Matcher(a)

    /* When */
    matcher.toBe(a)

    /* Then */
    awesome()
  }

  @Test
  fun toBe_withUnequalReferences_fails() {
    /* Given */
    val actual = Data(1)
    val expected = Data(1)
    val matcher = Matcher(actual)

    /* Then */
    expectErrorWithMessage("to be") when_ {

      matcher.toBe(expected)
    }
  }

  @Test
  fun toNotBe_withEqualReferences_fails() {
    /* Given */
    val a = Any()
    val matcher = Matcher(a)

    /* Then */
    expectErrorWithMessage("to not be") when_ {
      matcher.toNotBe(a)
    }
  }

  @Test
  fun toNotBe_withUnequalReferences_accepts() {
    /* Given */
    val actual = Data(1)
    val expected = Data(1)
    val matcher = Matcher(actual)

    /* Then */
    matcher.toNotBe(expected)

    /* Then */
    awesome()
  }

  @Test
  fun toBeEqualTo_withEqualReferences_areAccepted() {
    /* Given */
    val data = Data(1)
    val matcher = Matcher(data)

    /* When */
    matcher.toBeEqualTo(data)

    /* Then */
    awesome()
  }

  @Test
  fun toBeEqualTo_withEqualItems_areAccepted() {
    /* Given */
    val first = Data(1)
    val second = Data(1)
    val matcher = Matcher(first)

    /* When */
    matcher.toBeEqualTo(second)

    /* Then */
    awesome()
  }

  @Test
  fun toBeEqualTo_withSubtype_isNotAccepted() {
    /* Given */
    val first = Data(1)
    val second = Subdata(1)
    val matcher = Matcher(first)

    /* Then */
    expectErrorWithMessage("to be equal to").when_ {

      matcher.toBeEqualTo(second)
    }
  }

  @Test
  fun toBeEqualTo_withUnEqualItems_isNotAccepted() {
    /* Given */
    val first = 1
    val second = 2
    val matcher = Matcher(first)

    /* Then */
    expectErrorWithMessage("to be equal to").when_ {

      matcher.toBeEqualTo(second)
    }
  }

  @Test
  fun toBeEqualTo_withNullValue_isNotAccepted() {
    /* Given */
    val matcher = Matcher<Int>(null)

    /* Then */
    expectErrorWithMessage("to be equal to").when_ {

      matcher.toBeEqualTo(1)
    }
  }

  @Test
  fun toBeNull_withNullValue_accepts() {
    /* Given */
    val matcher = Matcher<Int>(null)

    /* When */
    matcher.toBeNull()

    /* Then */
    awesome()
  }

  @Test
  fun toBeNull_withNonNullValue_fails() {
    /* Given */
    val matcher = Matcher(3)

    /* Then */
    expectErrorWithMessage("Expected 3 to be null.") on {

      matcher.toBeNull()
    }
  }

  @Test
  fun toNotBeNull_withNullValue_fails() {
    /* Given */
    val matcher = Matcher<Int>(null)

    /* When */
    expectErrorWithMessage("to not be null") on {
      matcher.toNotBeNull()
    }

    /* Then */
    awesome()
  }

  @Test
  fun toNotBeNull_withNonNullValue_accepts() {
    /* Given */
    val matcher = Matcher(3)

    /* When */
    matcher.toNotBeNull()

    /* Then */
    awesome()
  }

  open class Data(val value: Int) {

    override fun equals(other: Any?): Boolean {
      if (this === other) {
        return true
      } else if (other != null && this.javaClass == other.javaClass) {
        val data = other as Data
        return this.value == data.value
      } else {
        return false
      }
    }
  }

  class Subdata(value: Int) : Data(value)
}
