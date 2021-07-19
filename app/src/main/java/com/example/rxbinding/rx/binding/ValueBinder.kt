package com.example.rxbinding.rx.binding

import com.jakewharton.rxrelay3.BehaviorRelay
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.Consumer

abstract class ValueBinder<T, in P : RelatedType, in C : RelatedType>(
    initialValue: T? = null
) : Binder<T, P, C>() {

    protected val behaviorRelay: BehaviorRelay<T> by lazy {
        initialValue?.let { BehaviorRelay.createDefault(it) } ?: BehaviorRelay.create()
    }

    internal val internalValue: T? get() = behaviorRelay.value

    val hasValue: Boolean get() = behaviorRelay.hasValue()

    override fun getConsumer(producer: P): Consumer<T> {
        return behaviorRelay
    }

    override fun getObservable(consumer: C): Observable<T> {
        return behaviorRelay.share()
    }

    fun update() {
        internalValue?.let(behaviorRelay::accept)
    }
}