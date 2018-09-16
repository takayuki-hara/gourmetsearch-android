package jp.co.penguin.gourmetsearch.data.api

import android.util.Log
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers
import jp.co.penguin.gourmetsearch.app.GourmetApplication
import jp.co.penguin.gourmetsearch.data.prefs.PrefsManager
import jp.co.penguin.gourmetsearch.data.api.response.GourmetSearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GourmetApiClient {
    val service by lazy {
        GourmetApiServiceProvider().createProvider()
    }
    var disposable: Disposable? = null

    fun gourmetSearch(keyword: String, area: String, course: Boolean, loaded: (GourmetSearchResponse?) -> Unit) {
        service.gourmetSearch(key = loadApiKey(), keyword = keyword, largeArea = area, course = course, format = "json").enqueue(object : Callback<GourmetSearchResponse> {
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

    // 元々のIFに合わせたため結局リスナーで返しているが、最終的にはこの中身をUseCaseのクラスで呼ぶなどすべきか
    fun gourmetSearchRx(keyword: String, area: String, course: Boolean, loaded: (GourmetSearchResponse?) -> Unit) {
        disposable = service.gourmetSearchRx(key = loadApiKey(), keyword = keyword, largeArea = area, course = course, format = "json")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> loaded(result) },
                        { error -> Log.e("Err", "network error!") }
                )
    }

    // HPG APIのアクセスキー
    private fun loadApiKey(): String {
        val context = GourmetApplication.applicationContext()
        return PrefsManager(context).getApiKey()
    }
}
