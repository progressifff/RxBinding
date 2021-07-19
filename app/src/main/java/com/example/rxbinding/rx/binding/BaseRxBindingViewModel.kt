package com.example.rxbinding.rx.binding

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.Consumer

abstract class BaseRxBindingViewModel : ViewModel(), Related<ViewModelRelatedType> {

    override val type = ViewModelRelatedType

    override fun <T : Any> bindingSubscribe(
        observable: Observable<out T>,
        consumer: Consumer<T>
    ): Disposable {
        return observable
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe(consumer::accept)
    }
}