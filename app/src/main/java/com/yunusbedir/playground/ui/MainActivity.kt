package com.yunusbedir.playground.ui

import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.yunusbedir.base.ui.BaseActivity
import com.yunusbedir.playground.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onBeforeSetContent() {
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.isLoading.value
            }
        }
    }

    override fun setupUI() {
    }

    override fun setupCollectAndObserve() {

    }

    override fun setupListeners() {

    }

}