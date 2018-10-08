package jp.co.penguin.gourmetsearch.tutrial

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class TutrialAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
    private val PAGE_NUM = 2

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return FirstFragment()
            1 -> return SecondFragment()
            else -> return FirstFragment()
        }
    }

    override fun getCount(): Int {
        return PAGE_NUM
    }
}
