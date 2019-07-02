package com.globant.equattrocchio.data.realm

import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.exceptions.RealmMigrationNeededException

class RealmInstance {

    private lateinit var mRealm: Realm

    fun getInstance(): Realm {

        if (!::mRealm.isInitialized) {
            val realmConfig = RealmConfiguration
                    .Builder()
                    .deleteRealmIfMigrationNeeded()
                    .build()
            try {
                mRealm = Realm.getInstance(realmConfig)
            } catch (e: RealmMigrationNeededException) {
                e.printStackTrace()
            }
        }
        return mRealm
    }
}
