package jp.co.penguin.gourmetsearch.mypage

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import jp.co.penguin.gourmetsearch.R
import jp.co.penguin.gourmetsearch.data.realm.dto.ShopObject
import java.util.ArrayList

class SimpleShopAdapter(val context: Context?) : RecyclerView.Adapter<SimpleShopAdapter.ViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var items: Array<ShopObject> = arrayOf<ShopObject>()
    private var listener: View.OnClickListener? = null

    fun refresh(items: Array<ShopObject>) {
        this.items = items
        notifyDataSetChanged()
    }

    fun getItem(position: Int) = this.items[position]

    fun setOnClickListener(onClickListener: View.OnClickListener) {
        this.listener = onClickListener
    }

    override fun getItemCount(): Int {
        return this.items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val root = this.inflater.inflate(R.layout.item_simple_shop, parent, false)

        root.setOnClickListener(this.listener)

        return ViewHolder(root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val shop = this.items[position]

        holder.genreText.text = shop.genreName
        holder.nameText.text = shop.name
        if (context != null) {
            Glide.with(context).load(shop.logoImage).into(holder.shopImage)
        }
        holder.budgetText.text = shop.budgetName
        holder.accessText.text = shop.mobileAccess
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var genreText: TextView = itemView.findViewById(R.id.genreText)
        var nameText: TextView = itemView.findViewById(R.id.nameText)
        var shopImage: ImageView = itemView.findViewById(R.id.shopImage)
        var budgetText: TextView = itemView.findViewById(R.id.budgetText)
        var accessText: TextView = itemView.findViewById(R.id.accessText)
    }

}