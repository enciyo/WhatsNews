package com.example.whatsnews.ui.webview

import android.os.AsyncTask
import android.os.Build
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.ValueCallback

import com.example.whatsnews.R
import kotlinx.android.synthetic.main.web_view_fragment.*
import android.webkit.WebSettings.PluginState
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView.RENDERER_PRIORITY_BOUND
import android.webkit.WebView.startSafeBrowsing
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.github.satoshun.reactivex.webkit.data.OnPageFinished
import com.github.satoshun.reactivex.webkit.data.OnPageStarted
import com.github.satoshun.reactivex.webkit.events
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable


class WebView : Fragment() {

    companion object {
        fun newInstance() = WebView()
    }

    private lateinit var viewModel: WebViewViewModel
    private val disposables = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.web_view_fragment, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(WebViewViewModel::class.java)
        val myurl: String = arguments!!.getString("url")!!
        disposables.add(
            webView.events(delegate = WebViewClient()).publish {shared ->
                Observable.merge(
                    shared.ofType(OnPageStarted::class.java),
                    shared.ofType(OnPageFinished::class.java)
                )
            }
                .subscribe())

        webView.settings.javaScriptEnabled=true
        webView.settings.useWideViewPort=true
        webView.settings.loadWithOverviewMode=true
        webView.loadUrl(myurl)


    }

    override fun onStop() {
        super.onStop()
        disposables.clear()
    }


}
