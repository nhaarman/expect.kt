/*
 * Copyright 2017 Niek Haarman
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

import io.reactivex.Observable
import org.junit.Test
import java.io.IOException

class TestObserverMatcherTest {

    @Test
    fun `expect no values - success`() {
        /* When */
        val observer = Observable.never<Unit>().test()

        /* Then */
        expect(observer).toHaveNoValues()
    }

    @Test
    fun `expect no values - failure`() {
        /* Given */
        val observer = Observable.just(Unit).test()

        /* Expect */
        expectErrorWithMessage("Expected no values") on {

            /* When */
            expect(observer).toHaveNoValues()
        }
    }

    @Test
    fun `expect value count - success`() {
        /* When */
        val observer = Observable.just(Unit).test()

        /* Then */
        expect(observer).toHaveValueCount(1)
    }

    @Test
    fun `expect value count - failure`() {
        /* When */
        val observer = Observable.just(Unit).test()

        /* Expect */
        expectErrorWithMessage("1 values were observed") on {
            /* Then */
            expect(observer).toHaveValueCount(3)
        }
    }

    @Test
    fun `expect values - success`() {
        /* When */
        val observer = Observable.just(Unit).test()

        /* Then */
        expect(observer).toHaveValues(Unit)
    }

    @Test
    fun `expect values - failure - invalid count`() {
        /* Given */
        val observer = Observable.just(Unit).test()

        /* Expect */
        expectErrorWithMessage("1 values were observed") on {

            /* When */
            expect(observer).toHaveValues(Unit, Unit)
        }
    }

    @Test
    fun `expect values - failure - invalid items`() {
        /* Given */
        val observer = Observable.just(1, 2).test()

        /* Expect */
        expectErrorWithMessage("but was") on {

            /* When */
            expect(observer).toHaveValues(1, 1)
        }
    }

    @Test
    fun `expect completion - success`() {
        /* Given */
        val observer = Observable.just(Unit).test()

        /* Then */
        expect(observer).toBeCompleted()
    }

    @Test
    fun `expect completion - failure`() {
        /* Given */
        val observer = Observable.never<Unit>().test()

        /* Expect */
        expectErrorWithMessage("Not completed") on {

            /* When */
            expect(observer).toBeCompleted()
        }
    }

    @Test
    fun `expect error - success`() {
        /* Given */
        val exception = IOException("")

        /* When */
        val observer = Observable.error<Unit>(exception).test()

        /* Then */
        expect(observer).toHaveError(exception)
    }

    @Test
    fun `expect error - failure - no error`() {
        /* Given */
        val exception = IOException("")
        val observer = Observable.never<Unit>().test()

        /* Expect */
        expectErrorWithMessage("No errors") on {
            /* When */
            expect(observer).toHaveError(exception)
        }
    }

    @Test
    fun `expect error - failure - different error`() {
        /* Given */
        val observer = Observable.error<Unit>(IOException("1")).test()

        /* Expect */
        expectErrorWithMessage("Error not present") on {

            /* When */
            expect(observer).toHaveError(IOException("2"))
        }
    }
}