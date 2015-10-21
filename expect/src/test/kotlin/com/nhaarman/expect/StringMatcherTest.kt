package com.nhaarman.expect

import org.junit.Test

class StringMatcherTest {

  @Test
  fun example_toContain() {
    val actual = "test"
    val expected = "es"

    expect(actual).toContain(expected)
  }

  @Test
  fun toBeEqualTo_withEqualStrings_accepts() {
    /* Given */
    val actual = "test"
    val expected = "test"
    val matcher = StringMatcher(actual)

    /* When */
    matcher.toBeEqualTo(expected)

    /* Then */
    awesome()
  }

  @Test
  fun toBeEqualTo_withUnEqualStrings_fails() {
    /* Given */
    val actual = "test"
    val expected = "nope"
    val matcher = StringMatcher(actual)

    /* Then */
    expectErrorWithMessage("to be equal to").when_ {

      matcher.toBeEqualTo(expected)
    }
  }

  @Test
  fun toContain_withEmptySubstring_accepts() {
    /* Given */
    val actual = "test"
    val expected = ""
    val matcher = StringMatcher(actual)

    /* When */
    matcher.toContain(expected)

    /* Then */
    awesome()
  }

  @Test
  fun toContain_withProperSubstring_accepts() {
    /* Given */
    val actual = "test"
    val expected = "es"
    val matcher = StringMatcher(actual)

    /* When */
    matcher.toContain(expected)

    /* Then */
    awesome()
  }

  @Test
  fun toContain_withEqualString_accepts() {
    /* Given */
    val actual = "test"
    val expected = "test"
    val matcher = StringMatcher(actual)

    /* When */
    matcher.toContain(expected)

    /* Then */
    awesome()
  }

  @Test
  fun toContain_withNonSubstring_fails() {
    /* Given */
    val actual = "test"
    val expected = "nope"
    val matcher = StringMatcher(actual)

    /* Then */
    expectErrorWithMessage("to contain") when_ {

      matcher.toContain(expected)
    }
  }

  @Test
  fun toContain_withNullValue_fails() {
    /* Given */
    val actual = null
    val expected = "nope"
    val matcher = StringMatcher(actual)

    /* Then */
    expectErrorWithMessage("but the actual value was null") when_ {

      matcher.toContain(expected)
    }
  }
}

