package com.example.rxbinding.rx.binding

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.Consumer

interface Related<Type : RelatedType> {

    val type: Type

    val <T : Any> ValueBinder<T, Type, *>.value: T?
        get() = internalValue

    val <T : Any> Binder<T, Type, *>.consumer: Consumer<T>
        get() = getConsumer(type)

    val <T : Any> Binder<T, *, Type>.observable: Observable<T>
        get() = getObservable(type)

    fun <T : Any> Binder<T, Type, *>.accept(value: T) {
        consumer.accept(value)
    }

    fun Binder<Unit, Type, *>.accept() {
        consumer.accept(Unit)
    }

    fun <T : Any> ValueBinder<T, Type, *>.change(block: (T) -> (T)) {
        internalValue?.let { consumer.accept(block(it)) }
    }

    infix fun <T : Any> Binder<T, *, Type>.bindTo(consumer: (T) -> Unit): Disposable {
        return bindingSubscribe(observable, consumer)
    }

    infix fun Binder<Unit, *, Type>.bindTo(consumer: () -> Unit): Disposable {
        return bindingSubscribe(observable) { consumer() }
    }

    infix fun <T : Any> Binder<T, *, Type>.bindTo(binder: Binder<T, Type, *>): Disposable {
        return bindingSubscribe(observable, binder.consumer)
    }

    infix fun <T : Any> Observable<out T>.bindTo(binder: Binder<T, Type, *>): Disposable {
        return bindingSubscribe(this, binder.consumer)
    }

    infix fun <T : Any> Observable<out T>.bindTo(consumer: (T) -> Unit): Disposable {
        return bindingSubscribe(this, consumer)
    }

    fun <T : Any> bindingSubscribe(
        observable: Observable<out T>,
        consumer: Consumer<T>
    ): Disposable
}