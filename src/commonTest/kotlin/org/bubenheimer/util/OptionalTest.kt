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

import kotlin.test.*

class OptionalTest {
    @Test
    fun testIsPresent() {
        assertFalse(Optional.empty.isPresent)
        assertFalse(Optional.ofNullable(null).isPresent)
        assertFalse(Optional.ofNullable<Boolean>(null).isPresent)
        assertTrue(Optional.ofNullable(false).isPresent)
        assertTrue(Optional.ofNullable(true).isPresent)
    }

    @Test
    fun testGetOrNull() {
        assertNull(Optional.empty.getOrNull())
        assertNull(Optional.ofNullable(null).getOrNull())
        assertNull(Optional.ofNullable<Boolean>(null).getOrNull())
        assertFalse(Optional.ofNullable(false).getOrNull()!!)
        assertTrue(Optional.ofNullable(true).getOrNull()!!)
        assertFalse(Optional.of(false).getOrNull()!!)
        assertTrue(Optional.of(true).getOrNull()!!)
    }

    @Test
    fun testGetOrThrow() {
        assertFailsWith(NullPointerException::class) { Optional.empty.getOrThrow() }
        assertFailsWith(NullPointerException::class) { Optional.ofNullable(null).getOrThrow() }
        assertFailsWith(NullPointerException::class) {
            Optional.ofNullable<Boolean>(null).getOrThrow()
        }
        assertFalse(Optional.ofNullable(false).getOrThrow())
        assertTrue(Optional.ofNullable(true).getOrThrow())
        assertFalse(Optional.of(false).getOrThrow())
        assertTrue(Optional.of(true).getOrThrow())
    }

    @Test
    fun testGetOrElse() {
        val other = Any()

        assertEquals(other, Optional.empty.getOrElse(other))
        assertEquals(false, Optional.empty.getOrElse(false))
        assertEquals(true, Optional.empty.getOrElse(true))

        assertEquals(other, Optional.ofNullable(other).getOrElse(other))
        assertEquals(false, Optional.ofNullable(false).getOrElse(other))
        assertEquals(true, Optional.ofNullable(true).getOrElse(other))

        assertFalse(Optional.ofNullable(null).getOrElse(false))
        assertFalse(Optional.ofNullable<Boolean>(null).getOrElse(false))
        assertFalse(Optional.ofNullable(false).getOrElse(true))
        assertTrue(Optional.ofNullable(true).getOrElse(false))

        assertEquals(false, Optional.of(false).getOrElse(other))
        assertEquals(true, Optional.of(true).getOrElse(other))
        assertFalse(Optional.of(false).getOrElse(true))
        assertTrue(Optional.of(true).getOrElse(false))
    }

    @Test
    fun testEquality() {
        assertEquals(Optional.empty, Optional.empty)
        assertEquals(Optional.empty, Optional.ofNullable(null))
        assertEquals(Optional.ofNullable(null), Optional.empty)
        assertEquals(Optional.ofNullable(null), Optional.ofNullable(null))

        assertEquals(Optional.of(123456), Optional.of(123456))
        assertEquals(Optional.ofNullable(123456), Optional.of(123456))
        assertEquals(Optional.of(123456), Optional.ofNullable(123456))
        assertEquals(Optional.ofNullable(123456), Optional.ofNullable(123456))

        assertNotEquals(Optional.of(7890), Optional.of(123456))
        assertNotEquals(Optional.ofNullable(7890), Optional.of(123456))
        assertNotEquals(Optional.of(7890), Optional.ofNullable(123456))
        assertNotEquals(Optional.ofNullable(7890), Optional.ofNullable(123456))

        assertNotEquals(Optional.empty, Optional.of(123456))
        assertNotEquals(Optional.of(123456), Optional.empty)
        assertNotEquals(Optional.empty, Optional.ofNullable(123456))
        assertNotEquals(Optional.ofNullable(123456), Optional.empty)

        assertNotEquals(Optional.ofNullable(null), Optional.of(123456))
        assertNotEquals(Optional.of(123456), Optional.ofNullable(null))
        assertNotEquals(Optional.ofNullable(null), Optional.ofNullable(123456))
        assertNotEquals(Optional.ofNullable(123456), Optional.ofNullable(null))

        assertNotEquals(Optional.ofNullable<Int>(null), Optional.of(123456))
        assertNotEquals(Optional.of(123456), Optional.ofNullable<Int>(null))
        assertNotEquals(Optional.ofNullable<Int>(null), Optional.ofNullable(123456))
        assertNotEquals(Optional.ofNullable(123456), Optional.ofNullable<Int>(null))
    }
}
