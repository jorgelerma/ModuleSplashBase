package com.globant.equattrocchio.cleanarchitecture.mvp.view.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.globant.equattrocchio.cleanarchitecture.R
import com.globant.equattrocchio.cleanarchitecture.models.ImageModel
import com.globant.equattrocchio.cleanarchitecture.mvp.view.base.MainActivity
import kotlinx.android.synthetic.main.item_image.view.*

class ImagesAdapter(private val context: MainActivity) :
        RecyclerView.Adapter<ImagesAdapter.MyViewHolder>() {

    private var imagesList = arrayListOf<ImageModel>()

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(context)
                .load(imagesList[position].url)
                .into(holder.imageIv)
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_image, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount() = imagesList.size

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageIv: ImageView = itemView.iv_image_holder
    }

    fun updateImagesList(incomingList: List<ImageModel>) {
        this.imagesList.clear()
        this.imagesList.addAll(incomingList)
        notifyDataSetChanged()
    }
}
