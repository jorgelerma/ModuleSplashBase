package com.globant.equattrocchio.cleanarchitecture.mvp.presenter

import com.globant.equattrocchio.cleanarchitecture.mvp.ImagesContract
import com.globant.equattrocchio.domain.models.ResultDomainInput
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito.`when`

class ImagesPresenterTest {

    private lateinit var view: ImagesContract.View
    private lateinit var model: ImagesContract.Model
    private lateinit var presenter: ImagesContract.Presenter
    private lateinit var observableMock: Observable<ResultDomainInput>
    private lateinit var compositeDisposable : Disposable
    private val SAMPLE_TEST = "response"

    @Before
    fun setUp() {
        view = mock()
        model = mock()
        observableMock = mock()
        presenter = ImagesPresenter(view, model)

        compositeDisposable = mock()

    }

    @Test
    fun showResponseTest(){
        // Act
        presenter.showResponse(SAMPLE_TEST)
        verify(view).showResult("response")
    }

    @Test
    fun callImagesTest(){
        // ARRANGE
        `when`(model.serviceRequestCall())
                .thenReturn(observableMock)
        // ACT
        presenter.callImages()
        // ASSERT
        verify(model).serviceRequestCall()
    }

    @Test
    fun disposeObserverTest(){
        presenter.disposeObserver()
        verify(compositeDisposable).dispose()
    }
}
