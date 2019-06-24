package com.globant.equattrocchio.domain

import com.globant.equattrocchio.domain.models.ResultDomainInput
import com.globant.equattrocchio.domain.service.ImageService
import io.reactivex.Observable
import javax.inject.Inject

class GetLatestImagesUseCase @Inject constructor(private val imageService: ImageService) {
    operator fun invoke(): Observable<ResultDomainInput> {
        return imageService.getLatestImages()
    }
}
