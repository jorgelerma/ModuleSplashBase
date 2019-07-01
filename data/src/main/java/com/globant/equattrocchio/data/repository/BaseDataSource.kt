package com.globant.equattrocchio.data.repository

import android.util.Log
import com.globant.equattrocchio.data.realm.RealmInstance
import com.globant.equattrocchio.data.realm.models.ResultCacheModel
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.realm.Realm

abstract class BaseDataSource{

    private lateinit var realmInstance: Realm

    open fun save(inputResult: Observable<ResultCacheModel>){

//        inputResult.map { persistData(it) }

        inputResult
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribeWith(object: Observer<ResultCacheModel>{
                    override fun onComplete() {
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(result: ResultCacheModel) {
                        persistData(result)
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })
    }

    fun getAll(): List<ResultCacheModel> {

        lateinit var imageCacheList: List<ResultCacheModel>
        realmInstance = getRealmInstance()

        if(realmInstance != null){
            imageCacheList = realmInstance.where(ResultCacheModel::class.java).findAll()
        }
        return imageCacheList
    }

    fun persistData(input: ResultCacheModel){
        realmInstance = getRealmInstance()

        if(realmInstance != null){
            Log.v(this.javaClass.simpleName, "***** mRealm not null on save, persistating list: ")

            realmInstance.use {
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
