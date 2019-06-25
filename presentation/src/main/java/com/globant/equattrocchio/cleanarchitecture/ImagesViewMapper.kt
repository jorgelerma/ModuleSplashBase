package com.globant.equattrocchio.cleanarchitecture

import com.globant.equattrocchio.cleanarchitecture.models.ImageView
import com.globant.equattrocchio.cleanarchitecture.models.ResultViewInput
import com.globant.equattrocchio.domain.models.Image
import com.globant.equattrocchio.domain.models.ResultDomainInput

interface ImagesViewMapper {

    fun mapDomainModelToPresentationModel(inputModel: ResultDomainInput): ResultViewInput
    fun mapImage(image: Image): ImageView
}

