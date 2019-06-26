package com.globant.equattrocchio.cleanarchitecture.mvp.view


import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.globant.equattrocchio.cleanarchitecture.R
import com.globant.equattrocchio.cleanarchitecture.models.ImageModel
import com.globant.equattrocchio.cleanarchitecture.mvp.ImagesContract
import com.globant.equattrocchio.cleanarchitecture.mvp.view.adapters.ImagesAdapter
import com.globant.equattrocchio.cleanarchitecture.mvp.view.base.MainActivity
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class ImagesView @Inject constructor(private val activityMain: MainActivity,
                                     private val statusSubject: PublishSubject<Boolean> ) : ImagesContract.View {

    private lateinit var recyclerView: RecyclerView
    private lateinit var imagesAdapter: ImagesAdapter
    private lateinit var linearlayoutManager: LinearLayoutManager

    override fun initAdapter() {
        recyclerView = activityMain.findViewById(R.id.images_recycler_view)
        linearlayoutManager = LinearLayoutManager(activityMain)
        imagesAdapter = ImagesAdapter(activityMain)
        recyclerView.apply {
            adapter = imagesAdapter
            layoutManager = linearlayoutManager
        }
    }

    override fun showError() {
        activityMain.tv_message_output.text = activityMain.getString(R.string.connection_error)
    }

    override fun setStatusSubject(status: Boolean) {
        statusSubject.onNext(status)
    }

    override fun updateImagesList(imagesList: List<ImageModel>){
        if(imagesList.isNotEmpty()){
            imagesAdapter.updateImagesList(imagesList)
        }
    }
}
