package com.globant.equattrocchio.cleanarchitecture.mvp.view

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.globant.equattrocchio.cleanarchitecture.R
import com.globant.equattrocchio.cleanarchitecture.models.ImageModel
import com.globant.equattrocchio.cleanarchitecture.models.ResultViewInput
import com.globant.equattrocchio.cleanarchitecture.mvp.ImagesContract
import com.globant.equattrocchio.cleanarchitecture.mvp.view.adapters.ImagesAdapter
import com.globant.equattrocchio.cleanarchitecture.mvp.view.base.MainActivity
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class ImagesView @Inject constructor(private val activityMain: MainActivity,
                                     private val statuSubject: PublishSubject<Boolean> ) : ImagesContract.View {

    private lateinit var imagesAdapter: ImagesAdapter

    private lateinit var recyclerView: RecyclerView
    private lateinit var imageList: ArrayList<ImageModel>


    override fun initRecyclerview() {

        getApplication
        recyclerView = activityMain.findViewById(R.id.rv_images_recycler_view)

        recyclerView.layoutManager = LinearLayoutManager(activityMain)

        imagesAdapter = ImagesAdapter(activityMain)
        recyclerView.adapter = imagesAdapter

        imageList = arrayListOf(ImageModel(32, "https://splashbase.s3.amazonaws.com/unsplash/regular/tumblr_mnh0n9pHJW1st5lhmo1_1280.jpg"))


    }




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

    override fun showImage(imagesList: ResultViewInput) {

        if(imagesList.images.isNotEmpty()){
            val imageUrl = imagesList.images.get(0).url
        }
        Log.d(this.javaClass.simpleName, " image list size: ${imagesList.images.size}")
    }

    override fun updateTheImagesLisg(){
        imagesAdapter.updateImagesList(imageList)
    }
}
