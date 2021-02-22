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

    if (obj != null) throw IllegalStateException("Object is not null: \"$obj\"  msg: \"$msg\"")
}

@ExperimentalContracts
public fun examNotNull(obj: Any?, msg: String = "") {
    contract { returns() implies (obj != null) }

    if (obj == null) throw IllegalStateException("Object is null  msg: \"$msg\"")
}

public inline fun <reified T> examIsInstance(value: Any?, msg: String = "") {
    if (value !is T) throw IllegalStateException(
        "Object \"$value\" not of type ${T::class}  msg: \"$msg\""
    )
}

public fun examEquals(expected: Int, actual: Int, msg: String = "") {
    if (expected != actual) throw IllegalStateException(
        "Objects not equal - expected: $expected  actual: $actual  msg: \"$msg\""
    )
}

public fun examEquals(expected: Long, actual: Long, msg: String = "") {
    if (expected != actual) throw IllegalStateException(
        "Objects not equal - expected: $expected  actual: $actual  msg: \"$msg\""
    )
}

public fun examEquals(expected: Short, actual: Short, msg: String = "") {
    if (expected != actual) throw IllegalStateException(
        "Objects not equal - expected: $expected  actual: $actual  msg: \"$msg\""
    )
}

public fun examEquals(expected: Byte, actual: Byte, msg: String = "") {
    if (expected != actual) throw IllegalStateException(
        "Objects not equal - expected: $expected  actual: $actual  msg: \"$msg\""
    )
}

public fun examEquals(expected: Char, actual: Char, msg: String = "") {
    if (expected != actual) throw IllegalStateException(
        "Objects not equal - expected: $expected  actual: $actual  msg: \"$msg\""
    )
}

public fun examEquals(expected: Any, actual: Any?, msg: String = "") {
    if (expected != actual) throw IllegalStateException(
        "Objects not equal - expected: \"$expected\"  actual: \"$actual\"  msg: \"$msg\""
    )
}

public fun examNotEquals(expected: Int, actual: Int, msg: String = "") {
    if (expected == actual) throw IllegalStateException(
        "Objects equal - expected: $expected  actual: $actual  msg: \"$msg\""
    )
}

public fun examNotEquals(expected: Long, actual: Long, msg: String = "") {
    if (expected == actual) throw IllegalStateException(
        "Objects equal - expected: $expected  actual: $actual  msg: \"$msg\""
    )
}

public fun examNotEquals(expected: Short, actual: Short, msg: String = "") {
    if (expected == actual) throw IllegalStateException(
        "Objects equal - expected: $expected  actual: $actual  msg: \"$msg\""
    )
}

public fun examNotEquals(expected: Byte, actual: Byte, msg: String = "") {
    if (expected == actual) throw IllegalStateException(
        "Objects equal - expected: $expected  actual: $actual  msg: \"$msg\""
    )
}

public fun examNotEquals(expected: Char, actual: Char, msg: String = "") {
    if (expected == actual) throw IllegalStateException(
        "Objects equal - expected: $expected  actual: $actual  msg: \"$msg\""
    )
}

public fun examNotEquals(expected: Any, actual: Any?, msg: String = "") {
    if (expected == actual) throw IllegalStateException(
        "Objects equal - expected: \"$expected\"  actual: \"$actual\"  msg: \"$msg\""
    )
}

public fun examSame(expected: Any, actual: Any, msg: String = "") {
    if (expected !== actual) throw IllegalStateException(
        "Objects not the same - expected: \"$expected\"  actual: \"$actual\"  msg: \"$msg\""
    )
}

public fun examNotSame(expected: Any, actual: Any?, msg: String = "") {
    if (expected === actual) throw IllegalStateException(
        "Objects are the same - expected: \"$expected\"  actual: \"$actual\"  msg: \"$msg\""
    )
}

public fun examLessThan(value1: Int, value2: Int, msg: String = "") {
    if (value1 >= value2) {
        throw IllegalStateException("$value1 is not less than $value2  msg: \"$msg\"")
    }
}

public fun examLessThan(value1: Long, value2: Long, msg: String = "") {
    if (value1 >= value2) {
        throw IllegalStateException("$value1 is not less than $value2  msg: \"$msg\"")
    }
}

public fun <T> examLessThan(value1: Comparable<T>, value2: T, msg: String = "") {
    if (value1 >= value2) {
        throw IllegalStateException("$value1 is not less than $value2  msg: \"$msg\"")
    }
}

public fun examLessOrEqual(value1: Int, value2: Int, msg: String = "") {
    if (value1 > value2) {
        throw IllegalStateException("$value1 is greater than $value2  msg: \"$msg\"")
    }
}

public fun examLessOrEqual(value1: Long, value2: Long, msg: String = "") {
    if (value1 > value2) {
        throw IllegalStateException("$value1 is greater than $value2  msg: \"$msg\"")
    }
}

public fun <T> examLessOrEqual(value1: Comparable<T>, value2: T, msg: String = "") {
    if (value1 > value2) {
        throw IllegalStateException("$value1 is greater than $value2  msg: \"$msg\"")
    }
}

public fun <T : Comparable<T>> examInRange(value: T, range: ClosedRange<T>, msg: String = "") {
    if (value !in range) throw IllegalStateException("$value is not in range $range  msg: \"$msg\"")
}

public fun <T> examIsOneOf(value: T, iterable: Iterable<T>, msg: String = "") {
    if (!iterable.contains(value)) {
        throw IllegalStateException("$value is not in iterable $iterable  msg: \"$msg\"")
    }
}

public fun examIsOneOf(value: Int, vararg set: Int, msg: String = "") {
    if (set.none { value == it }) {
        throw IllegalStateException("$value is not in set $set  msg: \"$msg\"")
    }
}

public fun examIsOneOf(value: Long, vararg set: Long, msg: String = "") {
    if (set.none { value == it }) {
        throw IllegalStateException("$value is not in set $set  msg: \"$msg\"")
    }
}

public fun examIsOneOf(value: Any?, vararg set: Any, msg: String = "") {
    if (set.none { value == it }) {
        throw IllegalStateException("$value is not in set $set  msg: \"$msg\"")
    }
}

public fun <T> examNotOneOf(value: T, iterable: Iterable<T>, msg: String = "") {
    if (iterable.contains(value)) {
        throw IllegalStateException("$value is in iterable $iterable  msg: \"$msg\"")
    }
}

public fun examNotOneOf(value: Int, vararg set: Int, msg: String = "") {
    if (set.any { value == it }) {
        throw IllegalStateException("$value is in set $set  msg: \"$msg\"")
    }
}

public fun examNotOneOf(value: Long, vararg set: Long, msg: String = "") {
    if (set.any { value == it }) {
        throw IllegalStateException("$value is in set $set  msg: \"$msg\"")
    }
}

public fun examNotOneOf(value: Any?, vararg set: Any, msg: String = "") {
    if (set.any { value == it }) throw IllegalStateException("$value is in set $set  msg: \"$msg\"")
}

@ExperimentalContracts
public fun examIsTrue(value: Boolean, msg: String = "") {
    contract { returns() implies value }

    if (!value) throw IllegalStateException("Condition was false  msg: \"$msg\"")
}

@ExperimentalContracts
public fun examIsFalse(value: Boolean, msg: String = "") {
    contract { returns() implies !value }

    if (value) throw IllegalStateException("Condition was true  msg: \"$msg\"")
}

public fun examFail(function: () -> Any?, msg: String = "") {
    try {
        function()
    } catch (e: Throwable) {
        return
    }
    throw IllegalStateException("Function did not fail  msg: \"$msg\"")
}

@ExperimentalContracts
public fun examFail(msg: String = ""): Nothing {
    contract { returns() implies false }

    throw IllegalStateException("Forced failure  msg: \"$msg\"")
}

public fun examNoFail(function: () -> Any?, msg: String = "") {
    try {
        function()
    } catch (e: Throwable) {
        throw IllegalStateException("Function failed with exception \"$e\"  msg: \"$msg\"", e)
    }
}
