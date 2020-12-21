package org.bubenheimer.util.flow

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.cancel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.*

public fun <A, B, R> Flow<A>.waitForLatestFrom(
    b: Flow<B>,
    transform: suspend (A, B) -> R
): Flow<R> = flow {
    coroutineScope {
        val bStateFlow = b
            // cancel parent scope on cancellation of child scope
            .onCompletion { if (it is CancellationException) this@coroutineScope.cancel(it) }
            .stateIn(this)

        collect { emit(transform(it, bStateFlow.value)) }
    }
}

public fun <A, B, C, R> Flow<A>.waitForLatestFrom(
    b: Flow<B>,
    c: Flow<C>,
    transform: suspend (A, B, C) -> R
): Flow<R> = flow {
    coroutineScope {
        val bStateFlow = b
            // cancel parent scope on cancellation of child scope
            .onCompletion { if (it is CancellationException) this@coroutineScope.cancel(it) }
            .stateIn(this)

        val cStateFlow = c
            // cancel parent scope on cancellation of child scope
            .onCompletion { if (it is CancellationException) this@coroutineScope.cancel(it) }
            .stateIn(this)

        collect { emit(transform(it, bStateFlow.value, cStateFlow.value)) }
    }
}

public fun <A, B, R> Flow<A>.transformAndWaitForLatestFrom(
    b: Flow<B>,
    transform: suspend FlowCollector<R>.(A, B) -> Unit
): Flow<R> = flow {
    coroutineScope {
        val bStateFlow = b
            // cancel parent scope on cancellation of child scope
            .onCompletion { if (it is CancellationException) this@coroutineScope.cancel(it) }
            .stateIn(this)

        collect { transform(it, bStateFlow.value) }
    }
}
