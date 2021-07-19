package com.example.rxbinding.rx.binding

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import io.reactivex.rxjava3.subjects.BehaviorSubject

class AndroidLifecycle(lifecycleOwner: LifecycleOwner) : LifecycleObserver {

    private val lifecycleSubject = BehaviorSubject.create<Lifecycle.Event>()

    init {
        lifecycleOwner.lifecycle.addObserver(this)
    }

    fun <T> getLifecycleBindTransformer(): RxLifecycleTransformer<T> {
        return RxLifecycleTransformer(lifecycleSubject)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    fun onEvent(owner: LifecycleOwner, event: Lifecycle.Event) {
        lifecycleSubject.onNext(event)
        if (event == Lifecycle.Event.ON_DESTROY) {
            owner.lifecycle.removeObserver(this)
        }
    }
}