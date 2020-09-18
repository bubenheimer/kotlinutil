/*
 * Copyright (c) 2015-2020 Uli Bubenheimer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.bubenheimer.util

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

@ExperimentalContracts
public fun examIsNull(obj: Any?, msg: String = "") {
    contract { returns() implies (obj == null) }

    if (obj != null) throw AssertionError("Object is not null: \"$obj\"  msg: \"$msg\"")
}

@ExperimentalContracts
public fun examNotNull(obj: Any?, msg: String = "") {
    contract { returns() implies (obj != null) }

    if (obj == null) throw AssertionError("Object is null  msg: \"$msg\"")
}

public fun examEquals(expected: Int, actual: Int, msg: String = "") {
    if (expected != actual) throw AssertionError(
        "Objects not equal - expected: $expected  actual: $actual  msg: \"$msg\""
    )
}

public fun examEquals(expected: Long, actual: Long, msg: String = "") {
    if (expected != actual) throw AssertionError(
        "Objects not equal - expected: $expected  actual: $actual  msg: \"$msg\""
    )
}

public fun examEquals(expected: Short, actual: Short, msg: String = "") {
    if (expected != actual) throw AssertionError(
        "Objects not equal - expected: $expected  actual: $actual  msg: \"$msg\""
    )
}

public fun examEquals(expected: Byte, actual: Byte, msg: String = "") {
    if (expected != actual) throw AssertionError(
        "Objects not equal - expected: $expected  actual: $actual  msg: \"$msg\""
    )
}

public fun examEquals(expected: Char, actual: Char, msg: String = "") {
    if (expected != actual) throw AssertionError(
        "Objects not equal - expected: $expected  actual: $actual  msg: \"$msg\""
    )
}

public fun examEquals(expected: Any, actual: Any?, msg: String = "") {
    if (expected != actual) throw AssertionError(
        "Objects not equal - expected: \"$expected\"  actual: \"$actual\"  msg: \"$msg\""
    )
}

public fun examNotEquals(expected: Int, actual: Int, msg: String = "") {
    if (expected == actual) throw AssertionError(
        "Objects equal - expected: $expected  actual: $actual  msg: \"$msg\""
    )
}

public fun examNotEquals(expected: Long, actual: Long, msg: String = "") {
    if (expected == actual) throw AssertionError(
        "Objects equal - expected: $expected  actual: $actual  msg: \"$msg\""
    )
}

public fun examNotEquals(expected: Short, actual: Short, msg: String = "") {
    if (expected == actual) throw AssertionError(
        "Objects equal - expected: $expected  actual: $actual  msg: \"$msg\""
    )
}

public fun examNotEquals(expected: Byte, actual: Byte, msg: String = "") {
    if (expected == actual) throw AssertionError(
        "Objects equal - expected: $expected  actual: $actual  msg: \"$msg\""
    )
}

public fun examNotEquals(expected: Char, actual: Char, msg: String = "") {
    if (expected == actual) throw AssertionError(
        "Objects equal - expected: $expected  actual: $actual  msg: \"$msg\""
    )
}

public fun examNotEquals(expected: Any, actual: Any?, msg: String = "") {
    if (expected == actual) throw AssertionError(
        "Objects equal - expected: \"$expected\"  actual: \"$actual\"  msg: \"$msg\""
    )
}

public fun examSame(expected: Any, actual: Any, msg: String = "") {
    if (expected !== actual) throw AssertionError(
        "Objects not the same - expected: \"$expected\"  actual: \"$actual\"  msg: \"$msg\""
    )
}

public fun examNotSame(expected: Any, actual: Any?, msg: String = "") {
    if (expected === actual) throw AssertionError(
        "Objects are the same - expected: \"$expected\"  actual: \"$actual\"  msg: \"$msg\""
    )
}

public fun examLessThan(value1: Int, value2: Int, msg: String = "") {
    if (value1 >= value2) throw AssertionError("$value1 is not less than $value2  msg: \"$msg\"")
}

public fun examLessThan(value1: Long, value2: Long, msg: String = "") {
    if (value1 >= value2) throw AssertionError("$value1 is not less than $value2  msg: \"$msg\"")
}

public fun examLessOrEqual(value1: Int, value2: Int, msg: String = "") {
    if (value1 > value2) throw AssertionError("$value1 is greater than $value2  msg: \"$msg\"")
}

public fun examLessOrEqual(value1: Long, value2: Long, msg: String = "") {
    if (value1 > value2) throw AssertionError("$value1 is greater than $value2  msg: \"$msg\"")
}

public fun examInRangeInclusive(lowerBound: Int, value: Int, upperBound: Int, msg: String = "") {
    if (lowerBound > value || value > upperBound) throw AssertionError(
        "$value is not in range [$lowerBound, $upperBound]  msg: \"$msg\""
    )
}

public fun examInRangeInclusive(lowerBound: Long, value: Long, upperBound: Long, msg: String = "") {
    if (lowerBound > value || value > upperBound) throw AssertionError(
        "$value is not in range [$lowerBound, $upperBound]  msg: \"$msg\""
    )
}

public fun examInRangeExclusive(lowerBound: Int, value: Int, upperBound: Int, msg: String = "") {
    if (lowerBound >= value || value >= upperBound) throw AssertionError(
        "$value is not in range ]$lowerBound, $upperBound[  msg: \"$msg\""
    )
}

public fun examInRangeExclusive(lowerBound: Long, value: Long, upperBound: Long, msg: String = "") {
    if (lowerBound >= value || value >= upperBound) throw AssertionError(
        "$value is not in range ]$lowerBound, $upperBound[  msg: \"$msg\""
    )
}

public fun examIsOneOf(value: Int, vararg set: Int, msg: String = "") {
    if (set.none { value == it }) throw AssertionError("$value is not in set $set  msg: \"$msg\"")
}

public fun examIsOneOf(value: Long, vararg set: Long, msg: String = "") {
    if (set.none { value == it }) throw AssertionError("$value is not in set $set  msg: \"$msg\"")
}

public fun examIsOneOf(value: Any?, vararg set: Any, msg: String = "") {
    if (set.none { value == it }) throw AssertionError("$value is not in set $set  msg: \"$msg\"")
}

public fun examNotOneOf(value: Int, vararg set: Int, msg: String = "") {
    if (set.any { value == it }) throw AssertionError("$value is in set $set  msg: \"$msg\"")
}

public fun examNotOneOf(value: Long, vararg set: Long, msg: String = "") {
    if (set.any { value == it }) throw AssertionError("$value is in set $set  msg: \"$msg\"")
}

public fun examNotOneOf(value: Any?, vararg set: Any, msg: String = "") {
    if (set.any { value == it }) throw AssertionError("$value is in set $set  msg: \"$msg\"")
}

@ExperimentalContracts
public fun examIsTrue(value: Boolean, msg: String = "") {
    contract { returns() implies value }

    if (!value) throw AssertionError("Condition was false  msg: \"$msg\"")
}

@ExperimentalContracts
public fun examIsFalse(value: Boolean, msg: String = "") {
    contract { returns() implies !value }

    if (value) throw AssertionError("Condition was true  msg: \"$msg\"")
}

public fun examFail(function: () -> Any?, msg: String = "") {
    try {
        function()
    } catch (e: Throwable) {
        return
    }
    throw AssertionError("Function did not fail  msg: \"$msg\"")
}

@ExperimentalContracts
public fun examFail(msg: String = ""): Nothing {
    contract { returns() implies false }

    throw AssertionError("Forced failure  msg: \"$msg\"")
}

public fun examNoFail(function: () -> Any?, msg: String = "") {
    try {
        function()
    } catch (e: Throwable) {
        throw AssertionError("Function failed with exception \"$e\"  msg: \"$msg\"", e)
    }
}

public fun examOnThread(thread: Thread, msg: String = "") {
    if (!thread.isCurrent()) throw AssertionError(
        "Not on expected thread \"$thread\"  msg: \"$msg\""
    )
}

public fun examOffThread(thread: Thread, msg: String = "") {
    if (thread.isCurrent()) throw AssertionError(
        "Not off thread \"$thread\"  msg: \"$msg\""
    )
}
