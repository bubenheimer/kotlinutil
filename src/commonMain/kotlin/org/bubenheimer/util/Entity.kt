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

import kotlin.jvm.JvmInline

@JvmInline
public value class Entity<out V> private constructor(private val value: Any?) {
    public val valid: Boolean
        get() = value !== Invalid

    public val invalid: Boolean
        get() = value === Invalid

    /**
     * @suppress Internal API
     */
    @PublishedApi
    internal val validValue: V
        @Suppress("UNCHECKED_CAST")
        get() = value as V

    override fun toString(): String {
        return if (valid) "Valid($validValue)" else "Invalid"
    }

    private object Invalid

    public companion object {
        public fun <T> Valid(value: T): Entity<T> = Entity(value)
        public fun <T> Invalid(): Entity<T> = Entity(Invalid)
    }
}

@Suppress("UNCHECKED_CAST")
public inline fun <R, V> Entity<V>.fold(onValid: (V) -> R, onInvalid: () -> R): R =
    if (valid) onValid(validValue) else onInvalid()

public inline fun <R, V : R> Entity<V>.getOrElse(onInvalid: () -> R): R =
    fold({ it }, onInvalid)

/**
 * @return A valid value (which may be `null`) or `null`
 */
@Suppress("UNCHECKED_CAST")
public fun <V> Entity<V>.getOrNull(): V? = getOrElse { null }

/**
 * @return A valid value
 * @throws IllegalStateException when the value is invalid
 */
@Suppress("UNCHECKED_CAST")
@Throws(IllegalStateException::class)
public fun <V> Entity<V>.getOrThrow(): V =
    getOrElse { throw IllegalStateException("Value is invalid") }
