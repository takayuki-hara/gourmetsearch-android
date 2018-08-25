package jp.co.penguin.gourmetsearch.data.samplecode

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class RandomUserApiService() {

    fun createService(): IRandomUserApiService {
        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://randomuser.me/")
                .build()
        return retrofit.create(IRandomUserApiService::class.java)
    }

}

interface IRandomUserApiService {
    @GET("api")
    fun apiDemo(): Call<RandomUser>
}

class SampleRetrofitClient(val listener: SampleRetrofitClientListener) {

    fun execute() {
        val service = RandomUserApiService().createService()
        service.apiDemo().enqueue(object : Callback<RandomUser> {
            override fun onFailure(call: Call<RandomUser>?, t: Throwable?) {
                Log.e("Err", "network error!")
            }

            override fun onResponse(call: Call<RandomUser>, response: Response<RandomUser>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        listener.onFetched(it)
                    }
                }
            }

        })
    }

    interface SampleRetrofitClientListener {
        fun onFetched(result: RandomUser)
    }

}

data class RandomUser (
        var results: List<Result>? = null,
        var info: Info? = null
)

data class Result (
    var gender: String? = null,
    var email: String? = null,
    var phone: String? = null,
    var cell: String? = null
)

data class Info (
    var seed: String? = null,
    var results: Int = 0,
    var page: Int = 0,
    var version: Float = 0.toFloat()
)

/* Usage:
    val client = SampleRetrofitClient(object : SampleRetrofitClient.SampleRetrofitClientListener {
        override fun onFetched(result: RandomUser) {
            print(result.info.toString())
        }
    }).execute()
 */