package com.example.rxbinding.rx.binding

interface RxBindingView : Related<ViewRelatedType> {
    override val type get() = ViewRelatedType
}
