package com.globant.equattrocchio.cleanarchitecture.mvp.view.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.globant.equattrocchio.cleanarchitecture.R
import com.globant.equattrocchio.cleanarchitecture.models.ImageModel
import com.globant.equattrocchio.cleanarchitecture.mvp.ImagesContract
import com.globant.equattrocchio.cleanarchitecture.mvp.view.ImagesView
import com.globant.equattrocchio.cleanarchitecture.mvp.view.adapters.ImagesAdapter
import dagger.android.AndroidInjection
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_recyclerview.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {




    private var requestStatus = false

    @Inject
    lateinit var statusSubject: PublishSubject<Boolean>

    @Inject
    lateinit var imagesPresenter: ImagesContract.Presenter

    @Inject
    lateinit var imagesView: ImagesView

//    private lateinit var imageList: ArrayList<ImageModel>

//    private lateinit var imagesAdapter: ImagesAdapter
//
//    private lateinit var recyclerView: RecyclerView

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {

        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imagesView.initRecyclerview()

        btn_call_service.setOnClickListener {
            imagesPresenter.requestLatestImages()
        }

        btn_search_query_request.setOnClickListener {
//            imagesPresenter.searchImages(et_input_query.text.trim().toString())
        }


        statusSubject.subscribe {
            setStatusSubject(it)
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

    private fun setStatusSubject(status: Boolean) {
        this.requestStatus = status
    }
}
