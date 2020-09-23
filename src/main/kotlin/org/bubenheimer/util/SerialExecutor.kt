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

import java.util.concurrent.Executor

public class SerialExecutor(private val executor: Executor) : Executor {
    private val deque: ArrayDeque<Runnable> = ArrayDeque()

    private var isRunning = false

    private fun work() {
        while (true) {
            synchronized(deque) {
                deque.removeFirstOrNull() ?: run {
                    isRunning = false
                    return
                }
            }.run()
        }
    }

    override fun execute(r: Runnable) {
        synchronized(deque) {
            deque.add(r)

            if (isRunning) return else isRunning = true
        }
        executor.execute { work() }
    }
}
