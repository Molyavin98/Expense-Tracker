package com.molyavin.expensetracker.presentation.screen.news

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.molyavin.expensetracker.di.scope.Injector
import com.molyavin.expensetracker.presentation.BaseSettingsScreen
import com.molyavin.expensetracker.presentation.ObserveLifecycleEvents


private val viewModel: NewsViewModel =
    Injector.INSTANCE.provideNewsViewModel()

@Composable
fun NewsScreen(navController: NavController) {
    viewModel.ObserveLifecycleEvents(LocalLifecycleOwner.current.lifecycle)

    val link by viewModel.link.collectAsState()

    BaseSettingsScreen(
        isLoading = false,
        navController = navController,
        showBottomNavBar = true
    ) {
        AndroidView(factory = {
            WebView(it).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                webViewClient = object : WebViewClient() {
                    @Deprecated("Deprecated in Java")
                    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                        view?.loadUrl(url ?: "")
                        return true
                    }
                }
            }
        }, update = {
            it.loadUrl(link)
        })
    }
}
