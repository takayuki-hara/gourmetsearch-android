package jp.co.penguin.gourmetsearch.data.realm.dto

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class ShopFavoriteObject : RealmObject() {
    @PrimaryKey
    var id: Long = 0
    var registeredAt: Date = Date()
    var updatedAt: Date = Date()
    lateinit var shopObj: ShopObject
}

var ShopFavoriteObject.shopObj : ShopObject
    get() = shopObj
    set(value) {
        shopObj = value
    }