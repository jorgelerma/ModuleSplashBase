package com.globant.equattrocchio.cleanarchitecture.mvp.view.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.globant.equattrocchio.cleanarchitecture.R
import com.globant.equattrocchio.cleanarchitecture.mvp.ImagesContract
import com.globant.equattrocchio.data.realm.RealmInstance
import dagger.android.AndroidInjection
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

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
    }

    @Override
    override fun onPause() {
        super.onPause()
        imagesPresenter.disposeObserver()
    }

    @Override
    override fun onDestroy() {
        super.onDestroy()
        if (realmInstance != null) {
            realmInstance.close()
        }
    }
}
