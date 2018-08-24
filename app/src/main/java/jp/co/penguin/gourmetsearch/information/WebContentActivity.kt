package jp.co.penguin.gourmetsearch.information

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebViewClient
import jp.co.penguin.gourmetsearch.R
import jp.co.penguin.gourmetsearch.information.InformationFragment
import kotlinx.android.synthetic.main.activity_web_content.*


class WebContentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_content)

        val intent = intent
        val url = intent.getStringExtra(InformationFragment.EXTRA_DATA)
        webView.webViewClient = WebViewClient()
        webView.loadUrl(url)
    }
}
