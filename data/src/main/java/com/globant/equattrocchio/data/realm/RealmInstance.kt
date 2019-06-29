package com.globant.equattrocchio.data.realm

import android.util.Log
import com.globant.equattrocchio.data.service.api.SplashbaseApi
import com.globant.equattrocchio.data.utils.Constants
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.exceptions.RealmMigrationNeededException
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RealmInstance {

    private lateinit var mRealm: Realm

    fun getInstance(): Realm {

        if(!::mRealm.isInitialized){
            val realmConfig = RealmConfiguration
                    .Builder()
                    .deleteRealmIfMigrationNeeded()
                    .build()

            try{
                mRealm = Realm.getInstance(realmConfig)

            }catch (e: RealmMigrationNeededException){
                e.printStackTrace()
            }

            Log.d(this.javaClass.simpleName, "***** Realm initialized: ")
        }
        return mRealm
    }
}


//Realm.init(this)
//val realmConfig = RealmConfiguration
//        .Builder()
//        .deleteRealmIfMigrationNeeded()
//        .build()
//
//try{
//    mRealm = Realm.getInstance(realmConfig)
//
//}catch (e: RealmMigrationNeededException){
//    e.printStackTrace()
//}
//
//Log.d(this.javaClass.simpleName, "***** Realm initialized: ")

//    private lateinit var retrofitInstance: SplashbaseApi
//
//    override fun getInstance(): SplashbaseApi {
//
//        if(!::retrofitInstance.isInitialized){
//            val retrofit = Retrofit.Builder()
//                    .baseUrl(Constants.BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                    .build()
//            retrofitInstance = retrofit.create(SplashbaseApi::class.java)
//        }
//        return retrofitInstance
//    }
//}