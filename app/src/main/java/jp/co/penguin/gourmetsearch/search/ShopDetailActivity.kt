package jp.co.penguin.gourmetsearch.search

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebViewClient
import jp.co.penguin.gourmetsearch.R
import kotlinx.android.synthetic.main.activity_web_content.*

class ShopDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_detail)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val intent = intent
        val url = intent.getStringExtra(SearchResultListFragment.EXTRA_DATA)
        webView.webViewClient = WebViewClient()
        webView.loadUrl(url)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
