package com.example.rxbinding.rx.binding

import com.example.rxbinding.util.Optional
import com.example.rxbinding.util.toOptional
import com.jakewharton.rxrelay3.BehaviorRelay
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.Consumer

class Command<T : Any>(
    initialValue: T? = null
) : Binder<T, ViewModelRelatedType, CommandRelatedType>() {

    private val behaviorRelay: BehaviorRelay<Optional<T>> by lazy {
        initialValue?.let { BehaviorRelay.createDefault(it.toOptional()) } ?: BehaviorRelay.create()
    }

    override fun getConsumer(producer: ViewModelRelatedType): Consumer<T> {
        return Consumer { behaviorRelay.accept(it.toOptional()) }
    }

    override fun getObservable(consumer: CommandRelatedType): Observable<T> {
        return behaviorRelay.share()
            .filter { it.hasValue }
            .map { it.get() }
            .doAfterNext { behaviorRelay.accept(Optional.empty()) }
    }
}
