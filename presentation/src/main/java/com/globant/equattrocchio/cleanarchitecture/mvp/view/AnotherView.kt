package com.globant.equattrocchio.cleanarchitecture.mvp.view

import com.globant.equattrocchio.cleanarchitecture.mvp.ImagesContract
import com.globant.equattrocchio.cleanarchitecture.mvp.view.base.MainActivity
import javax.inject.Inject

class AnotherView @Inject constructor(var mainActivity: MainActivity) : ImagesContract.View {
    override fun showError() {

    }

    override fun showResult(input: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setStatusSubject(status: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}