
package com.globant.equattrocchio.domain.service

import com.globant.equattrocchio.domain.models.ResultDomainInput
import io.reactivex.Observable


interface ImagesServicese {

    fun getLatestImages()

    fun getLatestImagesRx(): Observable<ResultDomainInput>
}