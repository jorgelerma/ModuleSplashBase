package com.globant.equattrocchio.domain

import com.globant.equattrocchio.domain.models.ResultDomainInput
import com.globant.equattrocchio.domain.service.ImageService
import io.reactivex.Observable
import javax.inject.Inject

class SearchImagesUseCase @Inject constructor(private val imageService: ImageService) : ImageUseCasesContract.SearchImagesContract{

    override fun searchImage(searchQuery: String): Observable<ResultDomainInput>{
        return imageService.searchImages(searchQuery)
    }
}
