package com.globant.equattrocchio.cleanarchitecture

import com.globant.equattrocchio.cleanarchitecture.models.ImageModel
import com.globant.equattrocchio.cleanarchitecture.models.ResultViewInput
import com.globant.equattrocchio.domain.models.Image
import com.globant.equattrocchio.domain.models.ResultDomainInput
import javax.inject.Inject

class ImagesViewMapperImpl @Inject constructor() : ImagesViewMapper {
    override fun mapDomainModelToPresentationModel(inputModel: ResultDomainInput): ResultViewInput {
        return ResultViewInput().apply {
            images = inputModel.images.map { mapImage(it) }
        }
    }

    override fun mapImage(image: Image): ImageModel {
        return ImageModel().apply {
            id = image.id
            url = image.url
        }
    }
}
