package com.example.rxbinding.rx.binding

class State<T : Any>(initialValue: T? = null) : ValueBinder<T, ViewModelRelatedType, StateRelatedType>(initialValue)