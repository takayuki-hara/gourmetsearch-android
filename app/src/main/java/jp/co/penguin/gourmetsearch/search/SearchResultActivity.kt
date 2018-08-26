package jp.co.penguin.gourmetsearch.search

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.view.MenuItem
import jp.co.penguin.gourmetsearch.R
import jp.co.penguin.gourmetsearch.data.api.GourmetApiClient
import kotlinx.android.synthetic.main.activity_search_result.*

class SearchResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val fragmentManager : FragmentManager = supportFragmentManager
        val adapter = SearchResultFragmentPagerAdapter(fragmentManager)
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)

        val client = GourmetApiClient()
        client.gourmetSearch(keyword = "焼き鳥", loaded = {
            print(it.toString())
        })
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
