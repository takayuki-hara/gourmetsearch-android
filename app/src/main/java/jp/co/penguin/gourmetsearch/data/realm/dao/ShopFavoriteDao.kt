package jp.co.penguin.gourmetsearch.data.realm.dao

import io.realm.Realm
import jp.co.penguin.gourmetsearch.data.realm.dto.ShopFavoriteObject
import jp.co.penguin.gourmetsearch.data.realm.dto.ShopObject

class ShopFavoriteDao {
    private fun getNextId(): Int {
        val realm = Realm.getDefaultInstance()
        var max = realm.where(ShopFavoriteObject::class.java).max("id") ?: 0
        return max.toInt() + 1
    }

    fun getAllFavorites(): Array<ShopFavoriteObject>? {
        val realm = Realm.getDefaultInstance()
        val result = realm.where(ShopFavoriteObject::class.java).findAll()
        val shops = result.toTypedArray()
        return shops
    }

    fun isExistFavorite(shopId: String): Boolean {
        val realm = Realm.getDefaultInstance()
        var result = realm.where(ShopFavoriteObject::class.java).equalTo("shopObj.id", shopId).findAll()
        return result.count() > 0
    }

    fun saveShopObject(shopObj: ShopObject): Boolean {
        if (isExistFavorite(shopObj.id)) {
            return false
        }

        val realm = Realm.getDefaultInstance()
        realm.executeTransaction { realm ->
            val favorite = realm.createObject(ShopFavoriteObject::class.java, getNextId())
            val managed = realm.copyToRealm(shopObj)
            favorite.shopObj = managed
        }
        return true
    }

    fun deleteShopObject(shopId: String) {
        val realm = Realm.getDefaultInstance()
        var result = realm.where(ShopFavoriteObject::class.java).equalTo("shopObj.id", shopId).findAll()
        realm.executeTransaction { realm ->
            result.deleteAllFromRealm()
        }
    }
}