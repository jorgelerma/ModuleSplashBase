package com.globant.equattrocchio.cleanarchitecture

import com.globant.equattrocchio.cleanarchitecture.models.ImageModel
import com.globant.equattrocchio.cleanarchitecture.models.ResultViewModel
import com.globant.equattrocchio.domain.models.Image
import com.globant.equattrocchio.domain.models.ResultDomainModel

interface ImagesViewMapper {

    fun mapDomainModelToPresentationModel(inputModel: ResultDomainModel): ResultViewModel

    fun mapImage(image: Image): ImageModel
}

