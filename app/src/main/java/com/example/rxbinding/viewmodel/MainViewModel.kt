package com.example.rxbinding.viewmodel

import com.example.rxbinding.rx.binding.Action
import com.example.rxbinding.rx.binding.BaseRxBindingViewModel
import com.example.rxbinding.rx.binding.Command

class MainViewModel : BaseRxBindingViewModel() {

    val typeAction = Action<CharSequence>()
    val showAction = Action<Unit>()
    val openResultScreenCommand = Command<CharSequence>()

    init {
        bind()
    }

    private fun bind() {
        showAction
            .observable
            .withLatestFrom(typeAction.observable, { _, typedText -> typedText })
            .bindTo(openResultScreenCommand)
    }
}