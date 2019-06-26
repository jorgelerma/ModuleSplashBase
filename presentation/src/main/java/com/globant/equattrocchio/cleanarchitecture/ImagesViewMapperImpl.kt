package com.globant.equattrocchio.cleanarchitecture

import com.globant.equattrocchio.cleanarchitecture.models.ImageModel
import com.globant.equattrocchio.cleanarchitecture.models.ResultViewModel
import com.globant.equattrocchio.domain.models.Image
import com.globant.equattrocchio.domain.models.ResultDomainModel
import javax.inject.Inject

class ImagesViewMapperImpl @Inject constructor() : ImagesViewMapper {
    override fun mapDomainModelToPresentationModel(inputModel: ResultDomainModel): ResultViewModel {
        return ResultViewModel().apply {
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
