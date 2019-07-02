package com.globant.equattrocchio.data.repository

import android.util.Log
import com.globant.equattrocchio.data.realm.RealmInstance
import com.globant.equattrocchio.data.realm.models.ResultCacheModel
import io.realm.Realm

abstract class BaseDataSource{

    lateinit var realmInstanceRef:Realm

    fun getAll(): List<ResultCacheModel> {

        lateinit var imageCacheList: List<ResultCacheModel>
        realmInstanceRef = getRealmInstance()

        if(realmInstanceRef != null){
            imageCacheList = realmInstanceRef.where(ResultCacheModel::class.java).findAll() as List<ResultCacheModel>
        }
        return imageCacheList
    }

    fun saveData(input: ResultCacheModel){

        realmInstanceRef = getRealmInstance()
        if(realmInstanceRef != null){
            Log.v(this.javaClass.simpleName, "***** mRealm not null on save, persistating list: ")
            realmInstanceRef.use {
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
