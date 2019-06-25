package com.globant.equattrocchio.cleanarchitecture.mvp.view

import android.util.Log
import com.bumptech.glide.Glide
import com.globant.equattrocchio.cleanarchitecture.R
import com.globant.equattrocchio.cleanarchitecture.models.ResultViewInput
import com.globant.equattrocchio.cleanarchitecture.mvp.ImagesContract
import com.globant.equattrocchio.cleanarchitecture.mvp.view.base.MainActivity
import com.globant.equattrocchio.domain.models.ResultDomainInput
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

    override fun showImage(image: ResultViewInput) {

        if(image.images.isNotEmpty()){
            val imageUrl = image.images.get(0).url
            Log.d(this.javaClass.simpleName, " urlss: $imageUrl")
            Glide.with(activityMain).load(imageUrl).into(activityMain.iv_image_holder)
        }
        Log.d(this.javaClass.simpleName, " image list size: ${image.images.size}")


    }

    override fun showImage() {
        var imageUrl = "https://splashbase.s3.amazonaws.com/unsplash/regular/tumblr_mnh0n9pHJW1st5lhmo1_1280.jpg"
        Glide.with(activityMain).load(imageUrl).into(activityMain.iv_image_holder)
    }
}
