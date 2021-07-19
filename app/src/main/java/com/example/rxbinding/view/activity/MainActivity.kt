package com.example.rxbinding.view.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.rxbinding.R
import io.reactivex.rxjava3.plugins.RxJavaPlugins

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RxJavaPlugins.setErrorHandler { Log.e("RxBinding", it.toString()) }
        setContentView(R.layout.activity_main)
    }
}
