package com.globant.equattrocchio.cleanarchitecture.mvp.view.base

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.globant.equattrocchio.cleanarchitecture.R
import com.globant.equattrocchio.cleanarchitecture.di.component.AppComponent
import com.globant.equattrocchio.cleanarchitecture.mvp.ImagesContract
//import com.globant.equattrocchio.cleanarchitecture.di.component.DaggerAppComponent
import com.globant.equattrocchio.cleanarchitecture.mvp.model.ImagesModel
import com.globant.equattrocchio.cleanarchitecture.mvp.presenter.ImagesPresenter
import com.globant.equattrocchio.cleanarchitecture.mvp.view.ImagesView
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity: AppCompatActivity() {

    private var requestStatus = false
    private val statusSubject: PublishSubject<Boolean> = PublishSubject.create<Boolean>()

    @Inject
    lateinit var presenter: ImagesContract.Presenter

//    @Inject
//    lateinit var imagesModel: ImagesModel
//
//    @Inject
//    lateinit var imagesView: ImagesView

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {

        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)

//        AndroidInjection.inject(this)

        setContentView(R.layout.activity_main)

//         getActivityComponent().inject(this)
//        DaggerAppComponent.builder().build()

//        DaggerAppComponent.builder()
//                .application(application)
//                .build()
//                .inject(this)


//        presenter = ImagesPresenter(imagesView, imagesModel)
//                ImagesView(this, statusSubject), imagesModel)
//                ImagesView(this), imagesModel)


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


//    private fun getActivityComponent(): AppComponent {
////        return DaggerAppComponent.builder().build()
//    }
}
