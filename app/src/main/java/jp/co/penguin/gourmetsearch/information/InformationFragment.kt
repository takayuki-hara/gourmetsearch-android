package jp.co.penguin.gourmetsearch.information

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity

import jp.co.penguin.gourmetsearch.R
import jp.co.penguin.gourmetsearch.information.model.AppInformation
import jp.co.penguin.gourmetsearch.information.model.AppInformationAction
import kotlinx.android.synthetic.main.fragment_information.view.*

class InformationFragment : Fragment() {

    /**
     * WebContentActivityに渡すURL
     */
    enum class WebContent(val url: String) {
        TERMS("https://gourmetsearch-dev.firebaseapp.com/policies/terms.html"),
        PRIVACY("https://gourmetsearch-dev.firebaseapp.com/policies/privacy.html"),
        GUIDELINE("https://gourmetsearch-dev.firebaseapp.com/policies/guideline.html")
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: InformationAdapter

    companion object {
        public const val EXTRA_DATA = "information.url"

        @JvmStatic
        fun newInstance() = InformationFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_information, container, false)

        root.licenseButton.setOnClickListener {
            val intent = Intent(activity, OssLicensesMenuActivity::class.java)
            intent.putExtra("title", "オープンソースライセンス")
            startActivity(intent)
        }

        root.termButton.setOnClickListener {
            val intent = Intent(activity, WebContentActivity::class.java)
            intent.putExtra(EXTRA_DATA, WebContent.TERMS.url)
            startActivity(intent)
        }

        this.recyclerView = root.findViewById(R.id.recyclerView)

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        this.adapter = InformationAdapter(activity)
        for(info in appInformations()) {
            this.adapter.add(info)
        }

        this.adapter.setOnClickListener(this.itemClickListener)

        this.recyclerView.adapter = adapter
        this.recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
    }

    private fun appInformations(): ArrayList<AppInformation> {
        val result = arrayListOf<AppInformation>()
        result.add(AppInformation(AppInformationAction.APIKEY, getString(R.string.text_apikey), null, null))
        result.add(AppInformation(AppInformationAction.URL, getString(R.string.text_terms), null, WebContent.TERMS.url))
        result.add(AppInformation(AppInformationAction.URL, getString(R.string.text_privacy), null, WebContent.PRIVACY.url))
        result.add(AppInformation(AppInformationAction.URL, getString(R.string.text_guideline), null, WebContent.GUIDELINE.url))
        result.add(AppInformation(AppInformationAction.LICENSE, getString(R.string.text_license), null, null))
        result.add(AppInformation(AppInformationAction.NONE, getString(R.string.text_version), getString(R.string.versionName), null))
        return result
    }

    private val itemClickListener = View.OnClickListener { view ->
        val position = recyclerView.getChildAdapterPosition(view)
        val appInformation = adapter.getItem(position)

        if (appInformation.action == AppInformationAction.URL) {
            val intent = Intent(activity, WebContentActivity::class.java)
            intent.putExtra(EXTRA_DATA, appInformation.url)
            startActivity(intent)
        }
        if (appInformation.action == AppInformationAction.LICENSE) {
            val intent = Intent(activity, OssLicensesMenuActivity::class.java)
            intent.putExtra("title", getString(R.string.text_license))
            startActivity(intent)
        }

    }
}
