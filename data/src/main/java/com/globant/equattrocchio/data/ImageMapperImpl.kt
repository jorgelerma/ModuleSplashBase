package com.globant.equattrocchio.data

import com.globant.equattrocchio.data.response.ImageModelCache
import com.globant.equattrocchio.data.response.ResultDataInput
import com.globant.equattrocchio.domain.models.Image
import com.globant.equattrocchio.domain.models.ResultDomainInput
import javax.inject.Inject

class ImageMapperImpl @Inject constructor() : ImageMapper {

    override fun mapDataModelToDomainModel(inputModel: ResultDataInput): ResultDomainInput {
        return ResultDomainInput().apply {
            images = inputModel.images.map { mapImage(it) }
        }
    }

    override fun mapImage(image: ImageModelCache): Image {
        return Image().apply {
            id = image.id
            url = image.url
            largeUrl = image.largeUrl
            sourceId = image.sourceId as String
        }
    }
}
