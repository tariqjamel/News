package com.example.newsapi

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var detailWebView = findViewById<WebView>(R.id.detailWebView)
        var webProgress = findViewById<ProgressBar>(R.id.webProgressBar)
        val url = intent.getStringExtra("URL")
        if (url!=null){
            detailWebView.settings.javaScriptEnabled= true
            detailWebView.webViewClient = object: WebViewClient() {
                override fun onPageFinished(view: WebView, url: String) {
                    super.onPageFinished(view, url)
                    webProgress.visibility = View.GONE
                    detailWebView.visibility = View.VISIBLE
                }
            }
            detailWebView.loadUrl(url)
        }
    }
}