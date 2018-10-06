package jp.co.penguin.gourmetsearch.mypage

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import jp.co.penguin.gourmetsearch.R
import jp.co.penguin.gourmetsearch.data.realm.dao.ShopFavoriteDao
import jp.co.penguin.gourmetsearch.data.realm.dao.ShopHistoryDao
import jp.co.penguin.gourmetsearch.data.realm.dto.ShopHistoryObject
import jp.co.penguin.gourmetsearch.data.realm.dto.ShopObject
import jp.co.penguin.gourmetsearch.search.ShopDetailActivity

class HistoryFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SimpleShopAdapter
    private var items: Array<ShopHistoryObject>? = null

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
        this.adapter.setShopItemListener(object: SimpleShopAdapter.ShopItemListener {
            override fun onItemClicked(position: Int) {
                showShopDetail(position)
            }

            override fun onFavoriteClicked(position: Int) {
                changeFavoriteState(position)
            }
        })

        this.recyclerView.adapter = adapter
        this.recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
    }

    override fun onResume() {
        super.onResume()
        setShopsToAdapter()
    }

    private fun setShopsToAdapter() {
        val tmp = ShopHistoryDao().getAllHistories()
        if (tmp != null) {
            val shops = Array<ShopObject>(tmp.count(), {tmp[it].shopObj})
            if (shops != null) {
                adapter.refresh(shops)
                items = tmp
            }
        }
    }

    private fun showShopDetail(position: Int) {
        val shop = adapter.getItem(position)
        val intent = Intent(activity, ShopDetailActivity::class.java)
        intent.putExtra(EXTRA_DATA, shop.shopUrl)
        startActivity(intent)
    }

    private fun changeFavoriteState(position: Int) {
        val shop = adapter.getItem(position)

        if (ShopFavoriteDao().isExistFavorite(shop.id)) {
            ShopFavoriteDao().deleteShopObject(shop.id)
            Toast.makeText(activity, R.string.msg_delete_favorite, Toast.LENGTH_SHORT).show()
        } else {
            ShopFavoriteDao().saveShopObject(shop)
            Toast.makeText(activity, R.string.msg_regist_favorite, Toast.LENGTH_SHORT).show()
        }

        // リストを更新
        setShopsToAdapter()
    }

}
