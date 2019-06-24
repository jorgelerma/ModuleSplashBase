package com.globant.equattrocchio.cleanarchitecture.mvp.view.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.globant.equattrocchio.cleanarchitecture.R
import com.globant.equattrocchio.cleanarchitecture.mvp.ImagesContract
import dagger.android.AndroidInjection
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private var requestStatus = false

    @Inject
    lateinit var statusSubject: PublishSubject<Boolean>

    @Inject
    lateinit var imagesPresenter: ImagesContract.Presenter

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {

        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_call_service.setOnClickListener {
            imagesPresenter.callImages()
        }

        statusSubject.subscribe {
            setStatusSubject(it)
        }
    }

    @Override
    override fun onResume() {
        super.onResume()
        if (requestStatus) {
            imagesPresenter.callImages()
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
