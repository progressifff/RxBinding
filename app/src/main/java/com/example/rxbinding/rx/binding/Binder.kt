package com.example.rxbinding.rx.binding

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.Consumer

abstract class Binder<T, in P : RelatedType, in C : RelatedType> {

    internal abstract fun getConsumer(producer: P): Consumer<T>

    internal abstract fun getObservable(consumer: C): Observable<T>
}