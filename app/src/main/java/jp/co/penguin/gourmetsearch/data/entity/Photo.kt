package jp.co.penguin.gourmetsearch.data.entity

import com.google.gson.annotations.SerializedName

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
