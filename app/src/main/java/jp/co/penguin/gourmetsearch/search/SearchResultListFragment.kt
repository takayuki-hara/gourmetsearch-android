package jp.co.penguin.gourmetsearch.search

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import jp.co.penguin.gourmetsearch.R
import jp.co.penguin.gourmetsearch.data.api.GourmetApiClient
import jp.co.penguin.gourmetsearch.data.entity.Shop

class SearchResultListFragment : Fragment() {

    private lateinit var listView: ListView

    companion object {
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

        val client = GourmetApiClient()
        client.gourmetSearch(keyword = "焼き鳥", loaded = {
            val adapter = SearchResultAdapter(activity)
            val shoplist = it?.results?.shop
            if (shoplist != null) {
                for (shop in shoplist) {
                    adapter.add(shop)
                }
            }
            this.listView.adapter = adapter
        })
    }

    private val itemClickListener = AdapterView.OnItemClickListener { adapterView, _, position, _ ->
        val shop = adapterView.getItemAtPosition(position) as Shop
    }
}
