package jp.co.penguin.gourmetsearch.data.api

import android.util.Log
import jp.co.penguin.gourmetsearch.data.response.GourmetSearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GourmetApiClient {
    fun gourmetSearch(keyword: String, area: String, course: Boolean, loaded: (GourmetSearchResponse?) -> Unit) {
        val key = loadApiKey()
        if (key == null) {
            loaded(null)
            return
        }
        val provider = GourmetApiServiceProvider().createProvider()
        provider.gourmetSearch(key = key, keyword = keyword, largeArea = area, course = course, format = "json").enqueue(object : Callback<GourmetSearchResponse> {
            override fun onFailure(call: Call<GourmetSearchResponse>?, t: Throwable?) {
                Log.e("Err", "network error!")
            }
            override fun onResponse(call: Call<GourmetSearchResponse>, response: Response<GourmetSearchResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        loaded(it)
                    }
                }
            }
        })
    }

    // HPG APIのアクセスキー
    private fun loadApiKey(): String? {
        // TODO: SharedPreferenceから読む
        return ""
    }
}
