package com.globant.equattrocchio.cleanarchitecture.mvp.view

import com.globant.equattrocchio.cleanarchitecture.R
import com.globant.equattrocchio.cleanarchitecture.models.ResultViewModel
import com.globant.equattrocchio.cleanarchitecture.mvp.ImagesContract
import com.globant.equattrocchio.cleanarchitecture.mvp.view.base.MainActivity
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class ImagesView @Inject constructor(private val activityMain: MainActivity,
                                     private val statuSubject: PublishSubject<Boolean> ) : ImagesContract.View {
    
    override fun showError() {
        activityMain.tv_message_output.text = activityMain.getString(R.string.connection_error)
    }

    override fun setStatusSubject(status: Boolean) {
        statuSubject.onNext(status)
    }

    override fun showImage(responseImagesList: ResultViewModel) {
        if(responseImagesList.images.isNotEmpty()){
            activityMain.updateImagesList(responseImagesList.images)
        }
    }
}
