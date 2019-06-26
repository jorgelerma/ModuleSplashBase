package com.globant.equattrocchio.cleanarchitecture.mvp.view

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.globant.equattrocchio.cleanarchitecture.R
import com.globant.equattrocchio.cleanarchitecture.models.ResultViewInput
import com.globant.equattrocchio.cleanarchitecture.mvp.ImagesContract
import com.globant.equattrocchio.cleanarchitecture.mvp.view.adapters.ImagesAdapter
import com.globant.equattrocchio.cleanarchitecture.mvp.view.base.MainActivity
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_recyclerview.*
import javax.inject.Inject

class ImagesView @Inject constructor(private val activityMain: MainActivity,
                                     private val statuSubject: PublishSubject<Boolean> ) : ImagesContract.View {


    private lateinit var recyclerView: RecyclerView
//    private var viewAdapter: RecyclerView.Adapter<*>

    private val imageAdapter = ImagesAdapter(activityMain)
//    private lateinit var viewManager: RecyclerView.LayoutManager
    private var viewManager = LinearLayoutManager(activityMain)



    init {

//        activityMain.images_recycler_view.layoutManager = LinearLayoutManager(activityMain)
//
//        activityMain.images_recycler_view.adapter = ImagesAdapter(activityMain)

        val recyclerView = activityMain.findViewById<RecyclerView>(R.id.images_recycler_view)

        recyclerView.layoutManager = LinearLayoutManager(activityMain)


////        viewManager = LinearLayoutManager(activityMain)
//        viewAdapter = imageAdapter
//
//        activityMain.findViewById<RecyclerView>(R.id.image_recycler_view).apply {
//            layoutManager = viewManager
//            adapter = imageAdapter
//        }

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
            Log.d(this.javaClass.simpleName, " urlss: $imageUrl")
            imageAdapter.updateImagesList(imagesList.images)
        }
        Log.d(this.javaClass.simpleName, " image list size: ${imagesList.images.size}")


    }
}
