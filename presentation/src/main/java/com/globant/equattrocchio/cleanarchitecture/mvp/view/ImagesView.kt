package com.globant.equattrocchio.cleanarchitecture.mvp.view

import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.globant.equattrocchio.cleanarchitecture.R
import com.globant.equattrocchio.cleanarchitecture.mvp.ImagesContract
import com.globant.equattrocchio.cleanarchitecture.util.bus.RxBus
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.CallServiceButtonObserver
import kotlinx.android.synthetic.main.activity_main.*

class ImagesView(private val activityMain: AppCompatActivity) : ImagesContract.View {
    override fun showError() {
        activityMain.tv_incoming_json.text = activityMain.getString(R.string.connection_error)
    }

    override fun showResult(input: String) {
        activityMain.tv_incoming_json.text = " input displayed: $input"
    }
}