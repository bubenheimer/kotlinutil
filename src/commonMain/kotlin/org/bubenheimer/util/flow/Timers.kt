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

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.takeWhile
import kotlin.time.Duration
import kotlin.time.TimeMark
import kotlin.time.TimeSource

public fun fixedDelayFlow(interval: Duration): Flow<Unit> = flow {
    while (true) {
        emit(Unit)
        delay(interval)
    }
}

public fun TimeSource.fixedRateFlow(interval: Duration): Flow<Unit> = flow {
    var nextMark = markNow()

    while (true) {
        emit(Unit)
        delay(interval - nextMark.elapsedNow())
        nextMark += interval
    }
}

public fun <T> Flow<T>.takeFor(
    interval: Duration,
    timeSource: TimeSource = TimeSource.Monotonic
): Flow<T> = flow {
    val endMark: TimeMark = timeSource.markNow() + interval

    emitAll(takeUntil(endMark))
}

public fun <T> Flow<T>.takeUntil(timeMark: TimeMark): Flow<T> =
    takeWhile { timeMark.hasNotPassedNow() }
