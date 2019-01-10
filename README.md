# Expect.kt

Expect.kt allows you to write test assertions in a readable way:

```kotlin
expect(3 + 2).toBeEqualTo(5)
expect(1 == 2).toBe(false)
expect(1 + 2).toBeGreaterThan(2)
```

You can also provide custom messages:

```kotlin
expect("expect.kt").toContain("pec") { "String did not contain 'pec'" }
```

Failure messages will be descriptive:

```kotlin
expect(3 + 2).toBeEqualTo(6)

java.lang.AssertionError: 
	Expected 5 to be equal to 6.
```

And:

```kotlin
val actual: String? = null
expect(actual).toContain("test")

java.lang.AssertionError: 
	Expected value to contain "test", but the actual value was null.
```

## Extensions

Due to Kotlin's [extensions](https://kotlinlang.org/docs/reference/extensions.html), we can easily add functionality to an existing matcher.

```kotlin
fun StringMatcher.toBeEmpty() {
  if (actual?.isEmpty() != true) {
    fail("Expected \"$actual\" to be empty.")
  }
}
```

We can now easily use this function in our tests:

```kotlin
expect("test").toBeEmpty()

java.lang.AssertionError: 
	Expected value to be empty, but actual value was "test".
```

## Custom matchers

You can also write matchers for custom types:

```kotlin
data class MyType(val name: String)

fun expect(actual: MyType?) : MyTypeMatcher {
  return MyTypeMatcher(actual)
}

class MyTypeMatcher(actual: MyType?) : Matcher<MyType>(actual) {

  fun toBeNamed(expected: String) {
    if(actual?.name != expected) {
      fail("Expected ${actual?.name} to be $expected")
    }
  }
}
```

## Available Matchers

### Generic Matchers

expect(actual).toBe(object)
expect(actual).toBeTheSameAs(object)
expect(actual).toNotBeTheSameAs(object)
expect(actual).toBeNull()
expect(actual).toNotBeNull()
expect(actual).toBeInstanceOf<Type>()

### Boolean Matchers

expect(actual).toHold()
expect(actual).notToHold()

### Error Matchers

expectErrorWithMessage(message).when_ { <something throws> }
expectErrorWithMessage(message).on { <something throws> }

### List Matchers

expect(actual).toBeEmpty()
expect(actual).toContain(object)
expect(actual).toHaveSize(size)

### Number Matchers

expect(actual).toBe(number)
expect(actual).toBeSmallerThan(number)
expect(actual).toBeGreaterThan(number)
expect(actual).toBeIn(number range)

### String Matchers

expect(actual).toBe(string)
expect(actual).toContain(string)

### Observer Matchers

expect(observer).toHaveNoValues()
expect(observer).toHaveValueCount(number)
expect(observer).toHaveValues(value[, value, ...])
expect(observer).toBeCompleted()
expect(observer).toHaveError(exception)

## Setup

Expect.kt is hosted on Maven Central.
To use Expect.kt in your gradle test builds, add the following to your `build.gradle`:

```groovy
testCompile 'com.nhaarman:expect.kt:x.x.x'
```

## License

    Copyright 2016 Niek Haarman

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
