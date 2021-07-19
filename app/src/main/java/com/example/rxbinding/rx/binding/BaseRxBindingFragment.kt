package com.example.rxbinding.rx.binding

import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.Consumer

open class BaseRxBindingFragment(@LayoutRes layoutRes: Int) : Fragment(layoutRes), RxBindingView {

    private val viewDisposable = CompositeDisposable()
    private val lifecycle by lazy { AndroidLifecycle(this) }

    @CallSuper
    override fun onDestroyView() {
        viewDisposable.clear()
        super.onDestroyView()
    }

    override fun <T : Any> bindingSubscribe(
        observable: Observable<out T>,
        consumer: Consumer<T>
    ): Disposable {
        return observable
            .subscribeOn(AndroidSchedulers.mainThread())
            .observeOn(AndroidSchedulers.mainThread())
            .compose(lifecycle.getLifecycleBindTransformer())
            .subscribe(consumer)
            .also(viewDisposable::add)
    }
}