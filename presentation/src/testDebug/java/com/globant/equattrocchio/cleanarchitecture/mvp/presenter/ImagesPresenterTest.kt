package com.globant.equattrocchio.cleanarchitecture.mvp.presenter

import com.globant.equattrocchio.cleanarchitecture.mvp.ImagesContract
import com.globant.equattrocchio.domain.models.Image
import com.globant.equattrocchio.domain.models.ResultDomainInput
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
    private lateinit var imageList: ArrayList<Image>
    private lateinit var resultDomainInput: ResultDomainInput

    @Before
    fun setUp() {
        view = mock()
        model = mock()
        presenter = ImagesPresenter(view, model)
        imageList = arrayListOf(Image(32, "droid.com", "www.android.com", "droid.com"))
        resultDomainInput = ResultDomainInput(imageList)
    }

    @Test
    fun showResponseTest() {
        presenter.showResponse(resultDomainInput)
        verify(view).showResult(resultDomainInput)
    }

    @Test
    fun callImagesTest() {
        `when`(model.serviceRequestCall())
                .thenReturn(Observable.just(resultDomainInput))
        presenter.requestLatestImages()
        verify(view).showResult(resultDomainInput)
    }
}
