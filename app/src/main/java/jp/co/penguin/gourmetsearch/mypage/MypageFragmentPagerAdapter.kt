package jp.co.penguin.gourmetsearch.mypage

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class MypageFragmentPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return FavoriteFragment()
            1 -> return HistoryFragment()
            else -> return FavoriteFragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return "お気に入り"
            1 -> return "検索履歴"
            else -> return "お気に入り"
        }
    }
}