package com.example.mygridview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView

class WebViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        var webView:WebView = findViewById(R.id.web_view)

        val url:String = intent.getStringExtra("URL").toString()
        webView.getSettings().setJavaScriptEnabled(true)
        webView.loadUrl(url)
    }
}