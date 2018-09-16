package jp.co.penguin.gourmetsearch.app

import android.app.Application
import android.content.Context
import io.realm.Realm

class GourmetApplication : Application() {
    init {
        instance = this
    }

    companion object {
        private var instance: GourmetApplication? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)
    }

}