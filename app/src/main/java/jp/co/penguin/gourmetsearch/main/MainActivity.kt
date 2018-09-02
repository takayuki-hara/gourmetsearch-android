package jp.co.penguin.gourmetsearch.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import jp.co.penguin.gourmetsearch.R
import jp.co.penguin.gourmetsearch.information.InformationFragment
import jp.co.penguin.gourmetsearch.mypage.MypageFragment
import jp.co.penguin.gourmetsearch.search.SearchFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_search -> {
                val fragment = SearchFragment.newInstance(param1 = "", param2 = "")
                supportFragmentManager.beginTransaction().replace(R.id.contentFrame, fragment).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_mypage -> {
                val fragment = MypageFragment.newInstance(param1 = "", param2 = "")
                supportFragmentManager.beginTransaction().replace(R.id.contentFrame, fragment).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_information -> {
                val fragment = InformationFragment.newInstance()
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

//        // FCMトークンの取得
//        val task = FirebaseInstanceId.getInstance().instanceId
//        task.addOnSuccessListener { token ->
//            Log.d("FCM", "Refreshed token: " + token)
//        }
    }
}
