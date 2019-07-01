package com.globant.equattrocchio.data.repository

import android.util.Log
import com.globant.equattrocchio.data.realm.RealmInstance
import com.globant.equattrocchio.data.realm.models.ResultCacheModel
import io.realm.Realm

abstract class BaseDataSource{

    private lateinit var realmInstance: Realm

    open fun save(input: ResultCacheModel?){

        realmInstance = getRealmInstance()
        Log.v(this.javaClass.simpleName, "***** @save ")

        if(realmInstance != null){
            Log.v(this.javaClass.simpleName, "***** mRealm not null on save, persistating list: ")

            realmInstance.use {
                it.executeTransaction {
                    it.insertOrUpdate(input)
                }
            }
        }
    }

    fun getAll(): List<ResultCacheModel> {

        var imageCacheArrayList: ArrayList<ResultCacheModel> = ArrayList()
        lateinit var imageCacheList: List<ResultCacheModel>
        realmInstance = getRealmInstance()

        if(realmInstance != null){
            Log.v(this.javaClass.simpleName, "***** mRealm not null on getAll: ")
            imageCacheList = realmInstance.where(ResultCacheModel::class.java).findAll()
//            imageCacheArrayList.addAll(imageCacheList)
        }

//        if(imageCacheArrayList.size <= 0){
//            imageCacheArrayList.add(ResultCacheModel())
//        }

        return imageCacheList
    }

    fun getRealmInstance(): Realm {
        return RealmInstance().getInstance()
    }
}
