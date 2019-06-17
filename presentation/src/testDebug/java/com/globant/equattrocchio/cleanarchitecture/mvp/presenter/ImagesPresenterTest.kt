package com.globant.equattrocchio.cleanarchitecture.mvp.presenter

import com.globant.equattrocchio.cleanarchitecture.mvp.ImagesContract
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class ImagesPresenterTest {

    private lateinit var view: ImagesContract.View
    private lateinit var model: ImagesContract.Model
    private lateinit var presenter: ImagesContract.Presenter
    private val SAMPLE_TEST = "response"

    @Before
    fun setUp() {
        view = mock()
        model = mock()
        presenter = ImagesPresenter(view, model)
    }

    @Test
    fun showResponseTest(){
        // Act
        presenter.showResponse(SAMPLE_TEST)
        verify(view).showResult("response")
    }

    @Test
    fun callImagesTest(){
        presenter.callImages()
        verify(model).serviceRequestCall()
    }

    @Test
    fun disposeObserverTest(){
        presenter.disposeObserver()
    }
}
