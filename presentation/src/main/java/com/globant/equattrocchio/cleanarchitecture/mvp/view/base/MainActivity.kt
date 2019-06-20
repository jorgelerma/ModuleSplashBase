package com.globant.equattrocchio.cleanarchitecture.mvp.view.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.globant.equattrocchio.cleanarchitecture.R
import com.globant.equattrocchio.cleanarchitecture.di.component.AppComponent
import com.globant.equattrocchio.cleanarchitecture.di.component.DaggerAppComponent
import com.globant.equattrocchio.cleanarchitecture.mvp.model.ImagesModel
import com.globant.equattrocchio.cleanarchitecture.mvp.presenter.ImagesPresenter
import com.globant.equattrocchio.cleanarchitecture.mvp.view.ImagesView
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity: AppCompatActivity() {

    private lateinit var presenter: ImagesPresenter
    private var requestStatus = false
    private val statusSubject: PublishSubject<Boolean> = PublishSubject.create<Boolean>()

    @Inject
    lateinit var imagesModel: ImagesModel

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        // getActivityComponent().inject(this)

        presenter = ImagesPresenter(
                ImagesView(this, statusSubject), imagesModel)

//                ImagesModel(GetLatestImagesUseCase(ImagesRepository(imageMapper, imagesApiService))))

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

    private fun getActivityComponent(): AppComponent{
        return DaggerAppComponent.builder().build()
    }
}
