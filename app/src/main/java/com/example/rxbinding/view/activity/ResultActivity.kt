package com.example.rxbinding.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.rxbinding.R

const val RESULT_TEXT_KEY = "RESULT_TEXT_KEY"

class ResultActivity : AppCompatActivity(R.layout.activity_result) {

    companion object {
        fun getIntent(context: Context, resultText: CharSequence): Intent {
            return Intent(context, ResultActivity::class.java).apply {
                putExtra(RESULT_TEXT_KEY, resultText)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        findViewById<TextView>(R.id.result_text).text =
            intent.getCharSequenceExtra(RESULT_TEXT_KEY) ?: ""
    }
}
