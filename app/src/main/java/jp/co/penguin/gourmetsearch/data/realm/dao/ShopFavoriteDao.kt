package jp.co.penguin.gourmetsearch.data.realm.dao

import io.realm.Realm
import jp.co.penguin.gourmetsearch.data.api.entity.Shop
import jp.co.penguin.gourmetsearch.data.realm.dto.ShopFavoriteObject
import jp.co.penguin.gourmetsearch.data.realm.dto.ShopObject

class ShopFavoriteDao {
    fun getNextId(): Long {
        val realm = Realm.getDefaultInstance()
        var max = realm.where(ShopFavoriteObject::class.java).max("id") ?: 0
        return max.toLong() + 1
    }

    fun getAllFavorites(): Array<ShopFavoriteObject>? {
        val realm = Realm.getDefaultInstance()
        val result = realm.where(ShopFavoriteObject::class.java).findAll()
        val shops = result.toTypedArray()
        return shops
    }

    fun getShopList(): Array<ShopObject>? {
        val realm = Realm.getDefaultInstance()
        val result = realm.where(ShopFavoriteObject::class.java).findAll()
        //Log.d("DAO", result.toString())

        val shops = Array<ShopObject>(result.count(), {result[it].shopObj})
        return shops
    }

    fun saveShopObject(shop: Shop) {
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction { realm ->
            val favorite = realm.createObject(ShopFavoriteObject::class.java, getNextId())
            val managed = realm.copyToRealm(ShopObject(shop))
            favorite.shopObj = managed
        }
    }

    fun saveShopObject(shopObj: ShopObject) {
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction { realm ->
            val favorite = realm.createObject(ShopFavoriteObject::class.java, getNextId())
            val managed = realm.copyToRealm(shopObj)
            favorite.shopObj = managed
        }
    }

}