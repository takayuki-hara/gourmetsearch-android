package jp.co.penguin.gourmetsearch.information

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import jp.co.penguin.gourmetsearch.R
import jp.co.penguin.gourmetsearch.information.model.AppInformation
import java.util.ArrayList

class InformationAdapter(context: Context?) : RecyclerView.Adapter<InformationAdapter.ViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private val items = ArrayList<AppInformation>()
    private var listener: View.OnClickListener? = null

    fun add(appInformation: AppInformation) {
        this.items.add(appInformation)
    }

    fun getItem(position: Int) = this.items[position]

    fun setOnClickListener(onClickListener: View.OnClickListener) {
        this.listener = onClickListener
    }

    override fun getItemCount(): Int {
        return this.items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val root = this.inflater.inflate(R.layout.item_information, parent, false)

        root.setOnClickListener(this.listener)

        return ViewHolder(root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val appInformation = this.items[position]

        holder.titleText.text = appInformation.title
        holder.subText.text = appInformation.sub
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleText: TextView = itemView.findViewById(R.id.titleText)
        val subText: TextView = itemView.findViewById(R.id.subText)
    }
}