package com.example.rxbinding.util

import java.io.Serializable

open class Optional<T : Any>
internal constructor(
    private val value: T?
) : Serializable {

    /**
     * @see [java.util.Optional.get]
     */
    fun get(): T {
        if (value == null) throw NoSuchElementException("No value present")
        return value
    }

    fun getOrNull(): T? = value

    /**
     * @see [java.util.Optional.isPresent]
     */
    val hasValue: Boolean get() = value != null

    /**
     * @see [java.util.Optional.ifPresent]
     */
    fun ifPresent(consumer: (T) -> Unit) {
        if (value != null) consumer(value)
    }

    /**
     * @see [java.util.Optional.empty]
     */
    object EMPTY : Optional<Any>(null)

    companion object {

        /**
         * @see [java.util.Optional.of]
         */
        fun <T : Any> of(value: T?): Optional<T> {
            return Optional(value)
        }

        /**
         * @see [java.util.Optional.empty]
         */
        @Suppress("UNCHECKED_CAST")
        fun <T : Any> empty(): Optional<T> {
            return EMPTY as Optional<T>
        }
    }
}

fun <T : Any> T?.toOptional() = Optional.of(this)