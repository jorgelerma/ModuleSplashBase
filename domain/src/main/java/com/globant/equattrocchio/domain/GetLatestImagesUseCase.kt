package com.globant.equattrocchio.domain

import com.globant.equattrocchio.domain.models.ResultDomainInput
import com.globant.equattrocchio.domain.service.ImagesServices
import io.reactivex.Observable

class GetLatestImagesUseCase(private val imagesService: ImagesServices) {
    operator fun invoke(): Observable<ResultDomainInput> {
        return imagesService.getLatestImagesRx()
    }
}
