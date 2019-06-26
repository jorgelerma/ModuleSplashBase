package com.globant.equattrocchio.domain

import com.globant.equattrocchio.domain.models.ResultDomainModel
import com.globant.equattrocchio.domain.service.ImageService
import io.reactivex.Observable
import javax.inject.Inject

class SearchImagesUseCase @Inject constructor(private val imageService: ImageService) : ImageUseCasesContract.SearchImages{

    override fun searchImages(searchQuery: String): Observable<ResultDomainModel>{
        return imageService.searchImages(searchQuery)
    }
}
