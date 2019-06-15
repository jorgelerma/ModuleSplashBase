package com.globant.equattrocchio.cleanarchitecture.mvp.view.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.globant.equattrocchio.cleanarchitecture.R
import com.globant.equattrocchio.cleanarchitecture.mvp.model.ImagesModel
import com.globant.equattrocchio.cleanarchitecture.mvp.presenter.ImagesPresenter
import com.globant.equattrocchio.cleanarchitecture.mvp.view.ImagesView
import com.globant.equattrocchio.data.ImageMapper
import com.globant.equattrocchio.data.ImageServiceImpl
import com.globant.equattrocchio.domain.GetLatestImagesUseCase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity() {

    private lateinit var presenter: ImagesPresenter

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = ImagesPresenter(
                ImagesView(this),
                ImagesModel(GetLatestImagesUseCase(ImageServiceImpl(ImageMapper()))))

        btn_call_service.setOnClickListener {
            presenter.callImages()
        }
    }

    @Override
    override fun onResume() {
        super.onResume()
    }

    @Override
    override fun onPause() {
        super.onPause()
    }
}
