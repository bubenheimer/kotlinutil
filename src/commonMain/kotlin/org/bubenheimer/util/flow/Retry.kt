/*
 * Copyright (c) 2015-2021 Uli Bubenheimer
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

import kotlinx.coroutines.flow.*

public fun <T> Flow<T>.retryAndReset(
    currentRetries: Int = Int.MAX_VALUE,
    totalRetries: Long = Long.MAX_VALUE,
    predicate: suspend (cause: Throwable) -> Boolean = { true }
): Flow<T> {
    require(0 < currentRetries) { "Current retries must be positive, got $currentRetries" }
    require(0L < totalRetries) { "Total retries must be positive, got $totalRetries" }
    return retryWhen { cause, currentAttempt, totalAttempt ->
        currentAttempt < currentRetries && totalAttempt < totalRetries && predicate(cause)
    }
}

public fun <T> Flow<T>.retryWhen(
    predicate:
    suspend FlowCollector<T>.(cause: Throwable, currentAttempt: Int, totalAttempt: Long) -> Boolean
): Flow<T> = flow {
    var currentAttempt = 0

    this@retryWhen
        .retryWhen { cause, totalAttempt -> predicate(cause, currentAttempt++, totalAttempt) }
        .collect {
            currentAttempt = 0 // reset on upstream flow success
            emit(it)
        }
}
