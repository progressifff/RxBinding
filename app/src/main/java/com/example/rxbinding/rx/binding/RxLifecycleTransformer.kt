package com.example.rxbinding.rx.binding

import androidx.lifecycle.Lifecycle
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableSource
import io.reactivex.rxjava3.core.ObservableTransformer

data class RxLifecycleTransformer<T>(
    private val lifecycleEvents: Observable<Lifecycle.Event>
) : ObservableTransformer<T, T> {

    override fun apply(upstream: Observable<T>): ObservableSource<T> {
        return upstream
            .takeUntil(lifecycleEvents.filter { it == Lifecycle.Event.ON_STOP })
            .repeatWhen { it.switchMap { lifecycleEvents.filter { event -> event == Lifecycle.Event.ON_START } } }
    }
}
