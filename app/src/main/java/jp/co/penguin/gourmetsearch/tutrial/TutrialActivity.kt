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

    }
}
