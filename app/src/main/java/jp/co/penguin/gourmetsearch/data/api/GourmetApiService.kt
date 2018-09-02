package jp.co.penguin.gourmetsearch.util.samplecode

import jp.co.penguin.gourmetsearch.data.response.GourmetSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GourmetApiService {
    @GET("hotpepper/gourmet/v1")
    fun gourmetSearch(@Query("key") key: String,
                      @Query("keyword") keyword: String,
                      @Query("large_area") largeArea: String,
                      @Query("format") format: String): Call<GourmetSearchResponse>
}
