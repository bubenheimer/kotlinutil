package org.bubenheimer.util

/**
 * Intended for turning `when` into an expression (exhaustive when)
 */
public val <T> T.expr: T get() = this
