package com.globant.equattrocchio.cleanarchitecture.mvp.view.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.globant.equattrocchio.cleanarchitecture.R
import com.globant.equattrocchio.cleanarchitecture.mvp.model.ImagesModel
import com.globant.equattrocchio.cleanarchitecture.mvp.presenter.ImagesPresenter
import com.globant.equattrocchio.cleanarchitecture.mvp.view.ImagesView
import com.globant.equattrocchio.data.ImageMapper
import com.globant.equattrocchio.data.ImagesRepository
import com.globant.equattrocchio.data.service.api.ImagesApi
import com.globant.equattrocchio.domain.GetLatestImagesUseCase
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity() {

    private lateinit var presenter: ImagesPresenter
    private var requestStatus = false
    private val statusSubject: PublishSubject<Boolean> = PublishSubject.create<Boolean>()

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = ImagesPresenter(
                ImagesView(this, statusSubject),
                ImagesModel(GetLatestImagesUseCase(ImagesRepository(ImageMapper(), ImagesApi()))))

        btn_call_service.setOnClickListener {
            presenter.callImages()
        }

        statusSubject.subscribe {
            setStatusSubject(it)
        }
    }

    @Override
    override fun onResume() {
        super.onResume()
        if(requestStatus) {
            this.presenter.callImages()
        }
    }

    @Override
    override fun onPause() {
        super.onPause()
        this.presenter.disposeObserver()
    }

    private fun setStatusSubject(status: Boolean){
        this.requestStatus = status
    }
}
