package com.example.rxbinding

import com.example.rxbinding.rx.binding.Action
import com.example.rxbinding.rx.binding.BaseRxBindingViewModel
import com.example.rxbinding.rx.binding.Command
import com.example.rxbinding.rx.binding.State

class TestRxBindingViewModel : BaseRxBindingViewModel() {

    val testMessageAction = Action<String>()
    val testMessageState = State<String>()
    val testMessageCommand = Command<String>()

    var messageFromView: String = ""
        private set

    init {
        testMessageAction bindTo ::messageFromView::set
    }

    fun emitStateMessage(message: String) {
        testMessageState.accept(message)
    }

    fun emitCommandMessage(message: String) {
        testMessageCommand.accept(message)
    }
}