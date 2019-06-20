package com.globant.equattrocchio.cleanarchitecture.mvp.presenter

import com.globant.equattrocchio.cleanarchitecture.mvp.ImagesContract
import com.globant.equattrocchio.data.response.ResultInput
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
//    private lateinit var compositeDisposable: CompositeDisposable

    @Before
    fun setUp() {
        view = mock()
        model = mock()
        observableMock = mock()
        compositeDisposable = mock()
        presenter = ImagesPresenter(view, model)

    }

    @Test
    fun showResponseTest(){
        // Act
        presenter.showResponse("response")
        verify(view).showResult("response")
    }

    @Test
    fun callImagesTest(){
        `when`(model.serviceRequestCall())
                .thenReturn(Observable.just(ResultDomainInput()))
        presenter.callImages()
//        verify(view).showResult()
    }

    @Test
    fun disposeObserverTest(){
        `when`(compositeDisposable.isDisposed)
                .thenReturn(false)
        presenter.disposeObserver()
//        verify(compositeDisposable).dispose()
    }
}
