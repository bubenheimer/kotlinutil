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

package org.bubenheimer.util.flow

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kotlin.time.Duration

/**
 * The returned flow passes through all elements but never completes normally.
 */
public fun <T> Flow<T>.keepAlive(): Flow<T> = channelFlow {
    collect(::send)
    awaitClose()
}.buffer(Channel.RENDEZVOUS)

// Modelled after Flow.withIndex()
public fun <T> Flow<T>.everyNth(n: Int): Flow<T> = flow {
    var index = n
    collect {
        if (--index == 0) {
            index = n
            emit(it)
        }
    }
}

public inline fun <T> Flow<T>.onFirst(crossinline action: suspend (T) -> Unit): Flow<T> =
    flow {
        var pastFirst = false

        collect {
            if (!pastFirst) {
                pastFirst = true
                action(it)
            }

            emit(it)
        }
    }

private val NIL = Any()

public fun <T> Flow<T>.filterByPrevious(filter: (T, T) -> Boolean): Flow<T> = flow {
    @Suppress("UNCHECKED_CAST")
    var previous: T = NIL as T
    collect {
        if (previous === NIL || filter(previous, it)) {
            previous = it
            emit(it)
        }
    }
}

public fun <T> Flow<T>.delay(duration: Duration): Flow<T> = flow {
    collect {
        delay(duration)
        emit(it)
    }
}

public fun Flow<Duration>.delay(): Flow<Unit> = flow {
    collect {
        delay(it)
        emit(Unit)
    }
}

public fun infiniteFlow(): Flow<Unit> = flow { while (true) emit(Unit) }

public fun <T, K> Flow<T>.groupBy(
    capacity: Int = Channel.RENDEZVOUS,
    onBufferOverflow: BufferOverflow = BufferOverflow.SUSPEND,
    onUndeliveredElement: ((T) -> Unit)? = null,
    keySelector: (T) -> K,
): Flow<Pair<K, Flow<T>>> = flow {
    val map: MutableMap<K, SendChannel<T>> = hashMapOf()

    try {
        collect {
            val key = keySelector(it)

            map.getOrPut(key) {
                Channel(capacity, onBufferOverflow, onUndeliveredElement)
                    .also {
                        @Suppress("NestedLambdaShadowedImplicitParameter")
                        emit(key to it.consumeAsFlow())
                    }
            }.send(it) //TODO catch the right exception(s) and take action
        }
    } finally {
        map.values.forEach { it.close() }
    }
}
