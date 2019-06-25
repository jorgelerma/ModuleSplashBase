package com.globant.equattrocchio.domain

import com.globant.equattrocchio.domain.models.ResultDomainInput
import com.globant.equattrocchio.domain.service.ImageService
import io.reactivex.Observable
import javax.inject.Inject

class GetLatestImagesUseCase @Inject constructor(private val imageService: ImageService) : ImageUseCasesContract.GetLatestImagesContract {

    override fun getLatestImages(): Observable<ResultDomainInput> {
        return imageService.getLatestImages()
    }
}
