package com.example.rxbinding.view.fragment

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.viewModels
import com.example.rxbinding.R
import com.example.rxbinding.rx.binding.BaseRxBindingFragment
import com.example.rxbinding.view.activity.ResultActivity
import com.example.rxbinding.viewmodel.MainViewModel
import com.jakewharton.rxbinding4.view.clicks
import com.jakewharton.rxbinding4.widget.textChanges

class MainFragment : BaseRxBindingFragment(R.layout.fragment_main) {

    private val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind(view)
    }

    private fun bind(v: View) {
        v.findViewById<EditText>(R.id.test_edit_text).textChanges() bindTo viewModel.typeAction
        v.findViewById<Button>(R.id.test_button).clicks() bindTo viewModel.showAction

        viewModel.openResultScreenCommand bindTo ::openResultScreen
    }

    private fun openResultScreen(resultText: CharSequence) {
        val resultScreenIntent = ResultActivity.getIntent(requireContext(), resultText)
        startActivity(resultScreenIntent)
    }
}
