package jp.co.penguin.gourmetsearch.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import jp.co.penguin.gourmetsearch.R
import jp.co.penguin.gourmetsearch.information.InformationFragment
import jp.co.penguin.gourmetsearch.mypage.MypageFragment
import jp.co.penguin.gourmetsearch.search.SearchFragment


class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_search -> {
                message.setText(R.string.title_search)
                val fragment = SearchFragment.newInstance(param1 = "", param2 = "")
                supportFragmentManager.beginTransaction().replace(R.id.contentFrame, fragment).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_mypage -> {
                message.setText(R.string.title_mypage)
                val fragment = MypageFragment.newInstance(param1 = "", param2 = "")
                supportFragmentManager.beginTransaction().replace(R.id.contentFrame, fragment).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_information -> {
                message.setText(R.string.title_information)
                val fragment = InformationFragment.newInstance(param1 = "", param2 = "")
                supportFragmentManager.beginTransaction().replace(R.id.contentFrame, fragment).commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        if (savedInstanceState == null) {
            val manager = supportFragmentManager
            val transaction = manager.beginTransaction()
            transaction.replace(R.id.contentFrame, SearchFragment())
            transaction.commit()
        }

        licenseButton.setOnClickListener {
            print("onclicklistere")


            val intent = Intent(application, OssLicensesMenuActivity::class.java)
            intent.putExtra("title", "オープンソースライセンス")
            startActivity(intent)
        }

//        // FCMトークンの取得
//        val task = FirebaseInstanceId.getInstance().instanceId
//        task.addOnSuccessListener { token ->
//            Log.d("FCM", "Refreshed token: " + token)
//        }
    }
}
