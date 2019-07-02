package com.globant.equattrocchio.data.repository

import com.globant.equattrocchio.data.realm.RealmInstance
import com.globant.equattrocchio.data.realm.models.ResultCacheModel
import io.realm.Realm

abstract class BaseDataSource {

    private lateinit var realmInstanceRef: Realm

    fun getAll(): List<ResultCacheModel> {

        lateinit var imageCacheList: List<ResultCacheModel>
        realmInstanceRef = getRealmInstance()

        if (realmInstanceRef != null) {
            imageCacheList = realmInstanceRef.where(ResultCacheModel::class.java).findAll() as List<ResultCacheModel>
        }
        return imageCacheList
    }

    fun saveData(input: ResultCacheModel) {

        realmInstanceRef = getRealmInstance()
        if (realmInstanceRef != null) {
            realmInstanceRef.use { it ->
                it.executeTransaction {
                    it.insertOrUpdate(input)
                }
            }
        }
    }

    private fun getRealmInstance(): Realm {
        return RealmInstance().getInstance()
    }
}
