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
import jp.co.penguin.gourmetsearch.data.realm.dao.ShopFavoriteDao
import jp.co.penguin.gourmetsearch.data.realm.dto.ShopFavoriteObject
import jp.co.penguin.gourmetsearch.data.realm.dto.ShopObject
import jp.co.penguin.gourmetsearch.search.ShopDetailActivity
import jp.co.penguin.gourmetsearch.util.AlertDialogFragment

class FavoriteFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SimpleShopAdapter
    private var items: Array<ShopFavoriteObject>? = null

    companion object {
        public const val EXTRA_DATA = "shop.url"

        fun newInstance() = FavoriteFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_favorite, container, false)

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
                deleteFavorite(position)
            }
        })

        this.recyclerView.adapter = adapter
        this.recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
    }

    override fun onResume() {
        super.onResume()
        setShopsToAdapter()
    }

    fun refresh() {
        setShopsToAdapter()
    }

    private fun setShopsToAdapter() {
        val tmp = ShopFavoriteDao().getAllFavorites()
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

    private fun deleteFavorite(position: Int) {
        val shop = adapter.getItem(position)
        val dialog = AlertDialogFragment.newInstance(
                title = getString(R.string.text_caution),
                message = getString(R.string.msg_caution_delete),
                lintener = object: AlertDialogFragment.AlertDialogListener {
            override fun onClickPositive() {
                // 削除する
                ShopFavoriteDao().deleteShopObject(shop.id)
                // リストを更新
                setShopsToAdapter()
            }
            override fun onClickNegative() {
                // 何もしない
            }
        })
        dialog.show(fragmentManager, "")
    }
}
