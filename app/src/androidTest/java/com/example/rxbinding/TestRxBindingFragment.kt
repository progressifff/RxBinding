package com.example.rxbinding

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.rxbinding.rx.binding.BaseRxBindingFragment

class TestRxBindingFragment : BaseRxBindingFragment(android.R.layout.list_content) {

    val viewModel by viewModels<TestRxBindingViewModel>()
    var messageFromModelState: String = ""
    private set

    var messageFromModelCommand: String = ""
        private set

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind()
    }

    fun emitMessage(message: String) {
        viewModel.testMessageAction.accept(message)
    }

    private fun bind() {
        viewModel.testMessageState bindTo ::messageFromModelState::set
        viewModel.testMessageCommand bindTo ::messageFromModelCommand::set
    }
}