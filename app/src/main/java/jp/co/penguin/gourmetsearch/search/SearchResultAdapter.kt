package jp.co.penguin.gourmetsearch.search

import android.support.v4.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import jp.co.penguin.gourmetsearch.R
import jp.co.penguin.gourmetsearch.data.entity.Shop

class SearchResultAdapter(context: FragmentActivity?) : ArrayAdapter<Shop>(context, 0) {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = if (convertView == null) {
            val root = this.inflater.inflate(R.layout.item_shop, parent, false)!!

            val vh = ViewHolder()
            vh.nameText = root.findViewById(R.id.text_name)

            root.tag = vh

            root
        } else {
            convertView
        }

        val shop = getItem(position)

        val vh = view.tag as ViewHolder
        vh.nameText.text = shop.name


        return view
    }

    private class ViewHolder {
        lateinit var nameText: TextView

    }
}