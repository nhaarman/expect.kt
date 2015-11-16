package com.nhaarman.expect

import org.junit.Test

class BooleanMatcherTest {

  @Test
  fun example_toHold() {
    expect(true).toHold()
  }

  @Test
  fun example_notToHold() {
    expect(false).notToHold()
  }

  @Test
  fun toHold_withTrueValue_accepts() {
    /* Given */
    val actual = true
    val matcher = BooleanMatcher(actual)

    /* When */
    matcher.toHold()

    /* Then */
    awesome()
  }

  @Test
  fun toHold_withFalseValue_fails() {
    /* Given */
    val actual = false
    val matcher = BooleanMatcher(actual)

    /* Then */
    expectErrorWithMessage("Expected false to be true.") on{

      matcher.toHold()
    }
  }

  @Test
  fun toHold_withNullValue_fails() {
    /* Given */
    val matcher = BooleanMatcher(null)

    /* Then */
    expectErrorWithMessage("Expected actual value to be true, but the actual value was null.") on{

      matcher.toHold()
    }
  }

  @Test
  fun notToHold_withFalseValue_accepts() {
    /* Given */
    val actual = false
    val matcher = BooleanMatcher(actual)

    /* When */
    matcher.notToHold()

    /* Then */
    awesome()
  }

  @Test
  fun notToHold_withTrueValue_fails() {
    /* Given */
    val actual = true
    val matcher = BooleanMatcher(actual)

    /* Then */
    expectErrorWithMessage("Expected true to be false.") on{

      matcher.notToHold()
    }
  }

  @Test
  fun notToHold_withNullValue_fails() {
    /* Given */
    val matcher = BooleanMatcher(null)

    /* Then */
    expectErrorWithMessage("Expected actual value to be false, but the actual value was null.") on{

      matcher.notToHold()
    }
  }
}
