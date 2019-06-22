package com.globant.equattrocchio.cleanarchitecture.mvp.view

import com.globant.equattrocchio.cleanarchitecture.R
import com.globant.equattrocchio.cleanarchitecture.mvp.ImagesContract
import com.globant.equattrocchio.cleanarchitecture.mvp.view.base.MainActivity
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class ImagesView @Inject constructor(private val activityMain: MainActivity) : ImagesContract.View {
    override fun showError() {
        activityMain.tv_incoming_json.text = activityMain.getString(R.string.connection_error)
    }

    override fun showResult(input: String) {
        activityMain.tv_incoming_json.text = " input displayed: $input"
    }

    override fun setStatusSubject(status: Boolean) {
//        statuSubject.onNext(status)
    }
}
