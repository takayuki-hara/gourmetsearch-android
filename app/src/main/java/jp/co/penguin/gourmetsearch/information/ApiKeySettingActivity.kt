package jp.co.penguin.gourmetsearch.information

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import jp.co.penguin.gourmetsearch.R
import jp.co.penguin.gourmetsearch.data.prefs.PrefsManager
import kotlinx.android.synthetic.main.activity_api_key_setting.*

class ApiKeySettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_api_key_setting)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        // 設定値を反映させる
        val prefs = PrefsManager(this)
        apiKeyText.setText(prefs.getApiKey())

        saveButton.setOnClickListener {
            val key = apiKeyText.text.toString()
            if (validationApikey(key)) {
                prefs.setApiKey(key)
                Toast.makeText(this, R.string.msg_save_apikey, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, R.string.msg_error_apikey_setting, Toast.LENGTH_SHORT).show()
            }
        }

        deleteButton.setOnClickListener {
            prefs.setApiKey("")
            Toast.makeText(this, R.string.msg_delete_apikey, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun validationApikey(key: String): Boolean {
        val regex = Regex("""[0-9a-zA-Z]+""")
        val matchResult = regex.matchEntire(key)
        if (key.length != 16 || !regex.matches(key)) {
            return false
        }
        return true
    }
}
