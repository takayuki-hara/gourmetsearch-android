package jp.co.penguin.gourmetsearch.tutrial

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import jp.co.penguin.gourmetsearch.R
import kotlinx.android.synthetic.main.activity_tutrial.*

class TutrialActivity : AppCompatActivity() {

    companion object {
        private var position: Int = 0
        private var pager: ViewPager? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutrial)

        val adapter = TutrialAdapter(supportFragmentManager)
        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(pos: Int, posOffset: Float, posOffsetPixels: Int) {}
            override fun onPageSelected(pos: Int) {
                position = pos
                if (pos == 0) {
                    footerButton.text = getString(R.string.btn_next)
                } else {
                    footerButton.text = getString(R.string.btn_start)
                }
            }

        })
        indicator.setupWithViewPager(viewPager, true)

        pager = viewPager

        footerButton.setOnClickListener {
            if (position == 0) {
                pager?.setCurrentItem(1, true)
            } else {
                finish()
            }
        }
    }
}
