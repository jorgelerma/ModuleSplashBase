package com.globant.equattrocchio.data

import com.globant.equattrocchio.data.response.ImageModelService
import com.globant.equattrocchio.data.response.ResultDataModel
import com.globant.equattrocchio.domain.models.Image
import com.globant.equattrocchio.domain.models.ResultDomainModel
import javax.inject.Inject

class ImageMapperImpl @Inject constructor() : ImageMapper {

    override fun mapDataModelToDomainModel(inputModel: ResultDataModel): ResultDomainModel {
        return ResultDomainModel().apply {
            images = inputModel.images.map { mapImage(it) }
        }
    }

    override fun mapImage(image: ImageModelService): Image {
        return Image().apply {
            id = image.id
            url = image.url
            largeUrl = image.largeUrl
            sourceId = image.sourceId as String
        }
    }
}
