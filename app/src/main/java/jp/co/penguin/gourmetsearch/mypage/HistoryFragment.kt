package jp.co.penguin.gourmetsearch.mypage

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jp.co.penguin.gourmetsearch.R
import jp.co.penguin.gourmetsearch.data.realm.dao.ShopHistoryDao
import jp.co.penguin.gourmetsearch.search.ShopDetailActivity

class HistoryFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SimpleShopAdapter

    companion object {
        public const val EXTRA_DATA = "shop.url"

        fun newInstance() = HistoryFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_history, container, false)

        this.recyclerView = root.findViewById(R.id.recyclerView)

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        this.adapter = SimpleShopAdapter(activity)
        this.adapter.setItemOnClickListener(this.itemClickListener)

        this.recyclerView.adapter = adapter
        this.recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
    }

    override fun onResume() {
        super.onResume()
        setShopsToAdapter()
    }

    private fun setShopsToAdapter() {
        val data = ShopHistoryDao().getShopList()
        if (data != null) {
            adapter.refresh(data)
        }
    }

    private val itemClickListener = View.OnClickListener { view ->
        val position = recyclerView.getChildAdapterPosition(view)
        val shop = adapter.getItem(position)

        val intent = Intent(activity, ShopDetailActivity::class.java)
        intent.putExtra(EXTRA_DATA, shop.shopUrl)
        startActivity(intent)
    }

}
