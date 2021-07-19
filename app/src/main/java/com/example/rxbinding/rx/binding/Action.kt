package com.example.rxbinding.rx.binding

class Action<T : Any>(initialValue: T? = null) : ValueBinder<T, ViewRelatedType, ViewModelRelatedType>(initialValue)