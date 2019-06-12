package com.globant.equattrocchio.cleanarchitecture.mvp.view


import android.provider.Settings.Global.getString
import android.support.v7.app.AppCompatActivity
import com.globant.equattrocchio.cleanarchitecture.R
import com.globant.equattrocchio.cleanarchitecture.util.bus.RxBus
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.CallServiceButtonObserver
import kotlinx.android.synthetic.main.activity_main.*

class ImagesView(private val activityMain: AppCompatActivity): ActivityView(activityMain) {

    fun showText(text: String){
        activityMain.tv_incoming_json.text = text
    }

    fun callServiceBtnPressed(){
        RxBus.post(CallServiceButtonObserver.CallServiceButtonPressed())
    }

    fun showError(){
        activityMain.tv_incoming_json.text = activityMain.getString(R.string.connection_error)
    }
}