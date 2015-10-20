package com.nhaarman.expect

import org.junit.Test

class IntMatcherTest {

  @Test
  fun example_toBeEqualTo() {
    expect(1).toBeEqualTo(1)
  }

  @Test
  fun example_toBeSmallerThan() {
    expect(1).toBeSmallerThan(2)
  }

  @Test
  fun example_toBeGreaterThan() {
    expect(1).toBeGreaterThan(0)
  }

  @Test
  fun toBeEqual_withEqualValues_accepts() {
    /* Given */
    val matcher = IntMatcher(1)

    /* When */
    matcher.toBeEqualTo(1)

    /* Then */
    awesome()
  }

  @Test
  fun toBeEqual_withUnEqualValues_fails() {
    /* Given */
    val matcher = IntMatcher(1)

    /* Then */
    expectErrorWithMessage("to be equal to").on {

      matcher.toBeEqualTo(2)
    }
  }

  @Test
  fun toBeSmallerThan_withSmallerActualValue_accepts() {
    /* Given */
    val actual = 1
    val expected = 2
    val matcher = IntMatcher(actual)

    /* When */
    matcher.toBeSmallerThan(expected)

    /* Then */
    awesome()
  }

  @Test
  fun toBeSmallerThan_withEqualActualValue_fails() {
    /* Given */
    val actual = 1
    val expected = 1
    val matcher = IntMatcher(actual)

    /* Then */
    expectErrorWithMessage("Expected 1 to be smaller than 1.") on{

      matcher.toBeSmallerThan(expected)
    }
  }

  @Test
  fun toBeSmallerThan_withGreaterActualValue_fails() {
    /* Given */
    val actual = 2
    val expected = 1
    val matcher = IntMatcher(actual)

    /* Then */
    expectErrorWithMessage("Expected 2 to be smaller than 1.") on{

      matcher.toBeSmallerThan(expected)
    }
  }

  @Test
  fun toBeSmallerThan_withNullActualValue_fails() {
    /* Given */
    val actual = null
    val expected = 1
    val matcher = IntMatcher(actual)

    /* Then */
    expectErrorWithMessage("Expected actual value to be smaller than 1, but actual value was null.") on{

      matcher.toBeSmallerThan(expected)
    }
  }

  @Test
  fun toBeGreaterThan_withGreaterActualValue_accepts() {
    /* Given */
    val actual = 2
    val expected = 1
    val matcher = IntMatcher(actual)

    /* When */
    matcher.toBeGreaterThan(expected)

    /* Then */
    awesome()
  }

  @Test
  fun toBeGreaterThan_withEqualActualValue_fails() {
    /* Given */
    val actual = 1
    val expected = 1
    val matcher = IntMatcher(actual)

    /* Then */
    expectErrorWithMessage("Expected 1 to be greater than 1.") on{

      matcher.toBeGreaterThan(expected)
    }
  }

  @Test
  fun toBeGreaterThan_withSmallerActualValue_fails() {
    /* Given */
    val actual = 1
    val expected = 2
    val matcher = IntMatcher(actual)

    /* Then */
    expectErrorWithMessage("Expected 1 to be greater than 2.") on{

      matcher.toBeGreaterThan(expected)
    }
  }

  @Test
  fun toBeGreaterThan_withNullActualValue_fails() {
    /* Given */
    val actual = null
    val expected = 1
    val matcher = IntMatcher(actual)

    /* Then */
    expectErrorWithMessage("Expected actual value to be greater than 1, but actual value was null.") on{

      matcher.toBeGreaterThan(expected)
    }
  }
}
