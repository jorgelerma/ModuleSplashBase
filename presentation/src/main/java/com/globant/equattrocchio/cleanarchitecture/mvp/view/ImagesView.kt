package com.globant.equattrocchio.cleanarchitecture.mvp.view

import android.util.Log
import com.globant.equattrocchio.cleanarchitecture.R
import com.globant.equattrocchio.cleanarchitecture.models.ResultViewInput
import com.globant.equattrocchio.cleanarchitecture.mvp.ImagesContract
import com.globant.equattrocchio.cleanarchitecture.mvp.view.base.MainActivity
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class ImagesView @Inject constructor(private val activityMain: MainActivity,
                                     private val statuSubject: PublishSubject<Boolean> ) : ImagesContract.View {
    
    override fun showError() {
        activityMain.tv_incoming_json.text = activityMain.getString(R.string.connection_error)
    }

    override fun showResult(input: ResultViewInput) {
        activityMain.tv_incoming_json.text = " input displayed: ${input.images.toString()}"
    }

    override fun setStatusSubject(status: Boolean) {
        Log.d(this.javaClass.simpleName, "Status set: $status")
        statuSubject.onNext(status)
    }

    override fun showImage(responseImagesList: ResultViewInput) {
        if(responseImagesList.images.isNotEmpty()){
            activityMain.updateImagesList(responseImagesList.images)
        }
    }
}
