package jp.co.penguin.gourmetsearch.search

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import jp.co.penguin.gourmetsearch.R
import jp.co.penguin.gourmetsearch.data.api.GourmetApiClient
import jp.co.penguin.gourmetsearch.data.entity.Shop
import jp.co.penguin.gourmetsearch.data.prefs.PrefsManager

class SearchResultListFragment : Fragment() {

    private lateinit var listView: ListView

    companion object {
        public const val EXTRA_DATA = "shop.url"

        fun newInstance() = SearchResultListFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_search_result_list, container, false)

        this.listView = root.findViewById(R.id.list)
        this.listView.onItemClickListener = this.itemClickListener

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val keyword = PrefsManager(activity).getKeyword()
        val client = GourmetApiClient()
        client.gourmetSearch(keyword = keyword, loaded = {
            val adapter = SearchResultAdapter(activity)
            val shoplist = it?.results?.shop
            if (shoplist != null) {
                for (shop in shoplist) {
                    adapter.add(shop)
                }
            } else {
                Toast.makeText(activity, R.string.msg_search_no_result, Toast.LENGTH_SHORT).show()
            }
            this.listView.adapter = adapter
        })
    }

    private val itemClickListener = AdapterView.OnItemClickListener { adapterView, _, position, _ ->
        val shop = adapterView.getItemAtPosition(position) as Shop

        val intent = Intent(activity, ShopDetailActivity::class.java)
        intent.putExtra(EXTRA_DATA, shop.urls?.pc)
        startActivity(intent)
    }
}
