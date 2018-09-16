package jp.co.penguin.gourmetsearch.app

import android.app.Application
import android.content.Context
import com.facebook.stetho.Stetho
import com.uphyca.stetho_realm.RealmInspectorModulesProvider
import io.realm.Realm
import jp.co.penguin.gourmetsearch.util.samplecode.SampleRealm

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

        //Realm.init(this)
        Realm.init(this)

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        //.enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build())
    }

}