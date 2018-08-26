package jp.co.penguin.gourmetsearch.data.entity

import com.google.gson.annotations.SerializedName

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

data class Shop (
        var id: String? = null,

        var name: String? = null,

        @SerializedName("logo_image")
        var logoImage: String? = null,

        @SerializedName("name_kana")
        var nameKana: String? = null,

        var address: String? = null,

        @SerializedName("station_name")
        var stationName: String? = null,

        @SerializedName("ktai_coupon")
        var ktaiCoupon: Int = 0,

        @SerializedName("large_service_area")
        var largeServiceArea: Area? = null,

        @SerializedName("service_area")
        var serviceArea: Area? = null,

        @SerializedName("large_area")
        var largeArea: Area? = null,

        @SerializedName("middle_area")
        var middleArea: Area? = null,

        @SerializedName("small_area")
        var smallArea: Area? = null,

        var lat: Double = 0.0,

        var lng: Double = 0.0,

        var genre: Genre? = null,

        @SerializedName("sub_genre")
        var subGenre: Genre? = null,

        var food: Food? = null,

        @SerializedName("sub_food")
        var subFood: Food? = null,

        var budget: Budget? = null,

        @SerializedName("budget_memo")
        var budgetMemo: String? = null,

        var catch: String? = null,

        var capacity: Int = 0,

        var access: String? = null,

        @SerializedName("mobile_access")
        var mobileAccess: String? = null,

        var urls: Urls? = null,

        var photo: Photo? = null

        // open以下は省略
)

data class Area (
        var code: String? = null,
        var name: String? = null
)

data class Genre (
        var code: String? = null,
        var name: String? = null,
        var catch: String? = null
)

data class Food (
        var code: String? = null,
        var name: String? = null
)

data class Budget (
        var code: String? = null,
        var name: String? = null,
        var average: String? = null
)

data class Photo (
        var pc: PhotoDetail? = null,
        var mobile: PhotoDetail? = null
)

data class PhotoDetail (
        @SerializedName("l")
        var large: String? = null,
        @SerializedName("m")
        var middle: String? = null,
        @SerializedName("s")
        var small: String? = null
)

data class Urls (
        var pc: String? = null,
        var sp: String? = null
)
