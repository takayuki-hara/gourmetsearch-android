package jp.co.penguin.gourmetsearch.data.realm.dao

import io.realm.Realm
import jp.co.penguin.gourmetsearch.data.api.entity.Shop
import jp.co.penguin.gourmetsearch.data.realm.dto.ShopHistoryObject
import jp.co.penguin.gourmetsearch.data.realm.dto.ShopObject

class ShopHistoryDao {
    private fun getNextId(): Int {
        val realm = Realm.getDefaultInstance()
        var max = realm.where(ShopHistoryObject::class.java).max("id") ?: 0
        return max.toInt() + 1
    }

    fun getAllHistories(): Array<ShopHistoryObject>? {
        val realm = Realm.getDefaultInstance()
        val result = realm.where(ShopHistoryObject::class.java).findAll()
        val shops = result.toTypedArray()
        return shops
    }

    fun saveShopObject(shop: Shop) {
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction { realm ->
            val history = realm.createObject(ShopHistoryObject::class.java, getNextId())
            val managed = realm.copyToRealm(ShopObject(shop))
            history.shopObj = managed
        }
    }
}