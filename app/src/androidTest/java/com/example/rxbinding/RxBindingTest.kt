package com.example.rxbinding

import androidx.fragment.app.testing.launchFragment
import androidx.lifecycle.Lifecycle
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

private const val BINDING_MESSAGE_FROM_VIEW = "Hi, model"
private const val BINDING_MESSAGE_FROM_MODEL = "Hi, view"

@RunWith(AndroidJUnit4::class)
class RxBindingTest {

    @Before
    fun setupClass() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun testActionFromView() {
        val scenario = launchFragment<TestRxBindingFragment>()
        scenario.onFragment { fragment ->
            fragment.emitMessage(BINDING_MESSAGE_FROM_VIEW)
            Assert.assertEquals(fragment.viewModel.messageFromView, BINDING_MESSAGE_FROM_VIEW)
        }
    }

    @Test
    fun checkStateToView() {
        val scenario = launchFragment<TestRxBindingFragment>()
        scenario.onFragment { fragment ->
            scenario.moveToState(Lifecycle.State.CREATED)
            fragment.viewModel.emitStateMessage(BINDING_MESSAGE_FROM_MODEL)
            Assert.assertNotSame(BINDING_MESSAGE_FROM_MODEL, fragment.messageFromModelState)
            scenario.moveToState(Lifecycle.State.STARTED)
            Assert.assertEquals(BINDING_MESSAGE_FROM_MODEL, fragment.messageFromModelState)
        }
    }

    @Test
    fun checkCommandToView() {
        val scenario = launchFragment<TestRxBindingFragment>()
        scenario.onFragment { fragment ->
            scenario.moveToState(Lifecycle.State.CREATED)
            fragment.viewModel.emitCommandMessage(BINDING_MESSAGE_FROM_MODEL)
            Assert.assertNotSame(BINDING_MESSAGE_FROM_MODEL, fragment.messageFromModelCommand)
            scenario.moveToState(Lifecycle.State.STARTED)
            Assert.assertNotSame(BINDING_MESSAGE_FROM_MODEL, fragment.messageFromModelCommand)
        }
    }
}