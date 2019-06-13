package com.globant.equattrocchio.cleanarchitecture.mvp.view.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.globant.equattrocchio.cleanarchitecture.R
import com.globant.equattrocchio.cleanarchitecture.mvp.presenter.ImagesPresenter
import com.globant.equattrocchio.cleanarchitecture.mvp.view.ImagesView
import com.globant.equattrocchio.data.ImagesServicesImpl
import com.globant.equattrocchio.domain.GetLatestImagesUseCase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity() {

    lateinit var presenter: ImagesPresenter
    lateinit var getLatestImagesUseCase: GetLatestImagesUseCase
    lateinit var imagesView: ImagesView

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imagesView = ImagesView(this)
        getLatestImagesUseCase  = GetLatestImagesUseCase(ImagesServicesImpl())
        presenter = ImagesPresenter(imagesView, getLatestImagesUseCase)

        btn_call_service.setOnClickListener {
            imagesView.callServiceBtnPressed()
        }

    }

    @Override
    override fun onResume() {
        super.onResume()
        presenter.register()
    }

    @Override
    override fun onPause() {
        super.onPause()
        presenter.unregister()
    }
}
