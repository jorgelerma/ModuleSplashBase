package com.globant.equattrocchio.domain

import com.globant.equattrocchio.domain.models.ResultDomainModel
import com.globant.equattrocchio.domain.service.ImageService
import io.reactivex.Observable
import javax.inject.Inject

class GetLatestImagesUseCase @Inject constructor(private val imageService: ImageService) : ImageUseCasesContract.GetLatestImages {

    override fun getLatestImages(): Observable<ResultDomainModel> {
        return imageService.getLatestImages()
    }
}
