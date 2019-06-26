package com.globant.equattrocchio.cleanarchitecture.mvp.presenter

import com.globant.equattrocchio.cleanarchitecture.models.ImageModel
import com.globant.equattrocchio.cleanarchitecture.models.ResultViewInput
import com.globant.equattrocchio.cleanarchitecture.mvp.ImagesContract
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`

class ImagesPresenterTest {

    private lateinit var view: ImagesContract.View
    private lateinit var model: ImagesContract.Model
    private lateinit var presenter: ImagesContract.Presenter
    private lateinit var imageList: ArrayList<ImageModel>
    private lateinit var resultViewInput: ResultViewInput

    @Before
    fun setUp() {
        view = mock()
        model = mock()
        presenter = ImagesPresenter(view, model)
        imageList = arrayListOf(ImageModel(32, "droid.com"))
        resultViewInput = ResultViewInput(imageList)
    }

    @Test
    fun showResponseTest() {
        presenter.showResponse(resultViewInput)
        verify(view).showResult(resultViewInput)
    }

    @Test
    fun callImagesTest() {
        `when`(model.serviceRequestCall())
                .thenReturn(Observable.just(resultViewInput))
        presenter.requestLatestImages()
        verify(view).showResult(resultViewInput)
    }
}
