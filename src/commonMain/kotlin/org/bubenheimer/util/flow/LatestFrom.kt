/*
 * Copyright (c) 2023 Uli Bubenheimer
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
 */

package org.bubenheimer.util.flow

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

public fun <A, B, R> Flow<A>.waitForLatestFrom(
    b: Flow<B>,
    transform: suspend (A, B) -> R
): Flow<R> = flow {
    coroutineScope {
        val bStateFlow: StateFlow<B> = b.stateFlow(this)
        collect { emit(transform(it, bStateFlow.value)) }
        // After primary flow completes normally, cancel any StateFlow generation sharing our scope
        currentCoroutineContext().cancelChildren()
    }
}

public fun <A, B, C, R> Flow<A>.waitForLatestFrom(
    b: Flow<B>,
    c: Flow<C>,
    transform: suspend (A, B, C) -> R
): Flow<R> = flow {
    coroutineScope {
        val bStateFlow = b.stateFlow(this)
        val cStateFlow = c.stateFlow(this)
        collect { emit(transform(it, bStateFlow.value, cStateFlow.value)) }
        // After primary flow completes normally, cancel any StateFlow generation sharing our scope
        currentCoroutineContext().cancelChildren()
    }
}

public fun <A, B, R> Flow<A>.transformAndWaitForLatestFrom(
    b: Flow<B>,
    transform: suspend FlowCollector<R>.(A, B) -> Unit
): Flow<R> = flow {
    coroutineScope {
        val bStateFlow = b.stateFlow(this)
        collect { transform(it, bStateFlow.value) }
        // After primary flow completes normally, cancel any StateFlow generation sharing our scope
        currentCoroutineContext().cancelChildren()
    }
}

private suspend fun <T> Flow<T>.stateFlow(scope: CoroutineScope): StateFlow<T> =
    this as? StateFlow ?: stateIn(scope)
