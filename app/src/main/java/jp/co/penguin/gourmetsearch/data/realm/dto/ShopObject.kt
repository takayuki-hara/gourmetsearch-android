package jp.co.penguin.gourmetsearch.data.realm.dto

import io.realm.RealmObject
import jp.co.penguin.gourmetsearch.data.api.entity.Shop

open class ShopObject() : RealmObject() {
    //@PrimaryKey
    var id: String = ""
    var genreName: String = ""
    var name: String = ""
    var logoImage: String? = null
    var budgetName: String? = null
    var mobileAccess: String? = null
    var shopUrl: String? = null
    var open: String? = null
    var shopDetailMemo: String? = null

    constructor(shop: Shop): this() {
        id = shop.id ?: ""
        genreName = shop.genre?.name ?: ""
        name = shop.name ?: ""
        logoImage = shop.logoImage
        budgetName = shop.budget?.name
        mobileAccess = shop.mobileAccess
        shopUrl = shop.urls?.pc
        open = shop.open
        shopDetailMemo = shop.shopDetailMemo
    }
}