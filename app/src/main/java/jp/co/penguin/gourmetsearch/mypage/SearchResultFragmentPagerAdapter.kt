package jp.co.penguin.gourmetsearch.mypage

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import jp.co.penguin.gourmetsearch.search.SearchResultListFragment
import jp.co.penguin.gourmetsearch.search.SearchResultMapFragment

class SearchResultFragmentPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return SearchResultListFragment()
            1 -> return SearchResultMapFragment()
            else -> return SearchResultListFragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return "リスト"
            1 -> return "地図"
            else -> return "リスト"
        }
    }
}