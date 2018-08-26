package jp.co.penguin.gourmetsearch.search

import android.support.v4.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import jp.co.penguin.gourmetsearch.R
import jp.co.penguin.gourmetsearch.data.entity.Shop

class SearchResultAdapter(context: FragmentActivity?) : ArrayAdapter<Shop>(context, 0) {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = if (convertView == null) {
            val root = this.inflater.inflate(R.layout.item_shop, parent, false)!!

            val vh = ViewHolder()
            vh.genreText = root.findViewById(R.id.genreText)
            vh.nameText = root.findViewById(R.id.nameText)
            vh.shopImage = root.findViewById(R.id.shopImage)
            vh.budgetText = root.findViewById(R.id.budgetText)
            vh.accessText = root.findViewById(R.id.accessText)
            vh.openText = root.findViewById(R.id.openText)
            vh.memoText = root.findViewById(R.id.memoText)

            root.tag = vh

            root
        } else {
            convertView
        }

        val shop = getItem(position)

        val vh = view.tag as ViewHolder
        vh.accessText.text = shop.genre?.name
        vh.nameText.text = shop.name
        Glide.with(view).load(shop.logoImage).into(vh.shopImage)
        vh.budgetText.text = shop.budget?.name
        vh.accessText.text = shop.mobileAccess
        vh.openText.text = shop.open
        vh.memoText.text = shop.shopDetailMemo

        return view
    }

    private class ViewHolder {
        lateinit var genreText: TextView
        lateinit var nameText: TextView
        lateinit var shopImage: ImageView
        lateinit var budgetText: TextView
        lateinit var accessText: TextView
        lateinit var openText: TextView
        lateinit var memoText: TextView
    }
}