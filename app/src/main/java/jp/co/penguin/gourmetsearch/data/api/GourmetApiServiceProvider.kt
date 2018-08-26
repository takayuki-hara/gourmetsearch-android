package jp.co.penguin.gourmetsearch.data.api

import jp.co.penguin.gourmetsearch.data.samplecode.GourmetApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GourmetApiServiceProvider {
    fun createProvider(): GourmetApiService {
        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://webservice.recruit.co.jp/")
                .build()
        return retrofit.create(GourmetApiService::class.java)
    }
}
