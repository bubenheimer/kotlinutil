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

@Suppress("NON_PUBLIC_PRIMARY_CONSTRUCTOR_OF_INLINE_CLASS")
public inline class Optional<out V : Any>
/**
 * @suppress Internal API
 */
@PublishedApi internal constructor(
    /**
     * @suppress Internal API
     */
    @PublishedApi internal val value: Any,
) {
    public inline val isPresent: Boolean
        get() = value !== Empty

    @Suppress("UNCHECKED_CAST", "NOTHING_TO_INLINE")
    public inline fun getOrNull(): V? = if (isPresent) value as V else null

    override fun toString(): String = getOrNull()?.let { "Optional[$it]" } ?: "Optional.empty"

    /**
     * @suppress Internal API
     */
    @PublishedApi
    internal object Empty

    @Suppress("NOTHING_TO_INLINE")
    public companion object {
        public inline fun <T : Any> of(value: T): Optional<T> = Optional(value)
        public inline fun <T : Any> ofNullable(value: T?): Optional<T> =
            value?.let { of(value) } ?: empty

        public val empty: Optional<Nothing> = Optional(Empty)
    }
}

@Suppress("NOTHING_TO_INLINE")
public inline fun <R : Any, V : R> Optional<V>.getOrElse(other: R): R = getOrNull() ?: other

@Throws(NullPointerException::class)
@Suppress("NOTHING_TO_INLINE")
public inline fun <V : Any> Optional<V>.getOrThrow(): V =
    getOrNull() ?: throw NullPointerException()
