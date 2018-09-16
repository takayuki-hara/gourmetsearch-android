package jp.co.penguin.gourmetsearch.data.api.response

import com.google.gson.annotations.SerializedName
import jp.co.penguin.gourmetsearch.data.entity.Shop

data class GourmetSearchResponse (
        var results: GourmetSearchResult
)

data class GourmetSearchResult (
        @SerializedName("api_version")
        var apiVersion: String? = null,

        @SerializedName("results_returned")
        var resultsReturned: String? = null,

        @SerializedName("results_start")
        var resultsStart: Int = 0,

        @SerializedName("results_available")
        var resultsAvailable: Int = 0,

        var shop: List<Shop>? = null
)
