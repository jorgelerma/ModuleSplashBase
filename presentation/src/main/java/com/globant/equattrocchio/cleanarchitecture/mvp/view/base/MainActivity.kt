package com.globant.equattrocchio.cleanarchitecture.mvp.view.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.globant.equattrocchio.cleanarchitecture.R
import com.globant.equattrocchio.cleanarchitecture.mvp.ImagesContract
import com.globant.equattrocchio.data.realm.RealmInstance
import dagger.android.AndroidInjection
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import io.realm.*
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private var requestStatus = false

    @Inject
    lateinit var statusSubject: PublishSubject<Boolean>

    @Inject
    lateinit var imagesPresenter: ImagesContract.Presenter

    private lateinit var realmInstance: Realm

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imagesPresenter.initAdapter()

        realmInstance = RealmInstance().getInstance()

        btn_call_service.setOnClickListener {
            imagesPresenter.requestLatestImages()
        }

        btn_search_request.setOnClickListener {
            imagesPresenter.searchImages(et_input_query.text.trim().toString())
        }

        statusSubject.subscribe {
            setStatusSubject(it)
        }

        Observable.fromCallable {
            "EMPTY"
        }
                .startWith("First of all!")
                .subscribe {
                    Log.e(this.javaClass.simpleName, it)
                }
    }

    @Override
    override fun onResume() {
        super.onResume()
        if (requestStatus) {
            imagesPresenter.requestLatestImages()
        }
    }

    @Override
    override fun onPause() {
        super.onPause()
        imagesPresenter.disposeObserver()
    }

    @Override
    override fun onStop() {
        super.onStop()

    }

    @Override
    override fun onDestroy() {
        super.onDestroy()
        if(realmInstance != null){
            realmInstance.close()
        }
    }

    private fun setStatusSubject(status: Boolean) {
        this.requestStatus = status
    }
}
