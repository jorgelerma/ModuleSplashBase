package com.globant.equattrocchio.data.repository

import android.util.Log
import com.globant.equattrocchio.data.realm.RealmInstance
import com.globant.equattrocchio.data.realm.models.ResultCacheModel
import io.realm.Realm

abstract class BaseDataSource{

    private lateinit var realmInstance: Realm

    open fun save(input: ResultCacheModel){

        realmInstance = getRealmInstance()
        Log.d(this.javaClass.simpleName, "***** @save ")

        if(realmInstance != null){
            Log.d(this.javaClass.simpleName, "***** mRealm not null on save, persistating list: ")

            realmInstance.use {
                it.executeTransaction {
                    it.insertOrUpdate(input)
                }
            }
        }
    }

    fun getAll(): ResultCacheModel {

        var imageCacheArrayList: ArrayList<ResultCacheModel> = ArrayList()
        realmInstance = getRealmInstance()

        if(realmInstance != null){
            Log.d(this.javaClass.simpleName, "***** mRealm not null on getAll: ")
            val imageCacheList = realmInstance.where(ResultCacheModel::class.java).findAll()
            imageCacheArrayList.addAll(imageCacheList)
        }

        if(imageCacheArrayList.size <= 0){
            imageCacheArrayList.add(ResultCacheModel())
        }

        return imageCacheArrayList[0]
    }

    fun getRealmInstance(): Realm {
        return RealmInstance().getInstance()
    }
}
