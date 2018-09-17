package jp.co.penguin.gourmetsearch.mypage

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jp.co.penguin.gourmetsearch.R
import kotlinx.android.synthetic.main.fragment_mypage.*

class MypageFragment : Fragment() {

    companion object {
        fun newInstance() = MypageFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_mypage, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val fragmentManager : FragmentManager = childFragmentManager
        val adapter = MypageFragmentPagerAdapter(fragmentManager)
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
    }
}
