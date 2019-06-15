package com.globant.equattrocchio.data

import com.globant.equattrocchio.data.response.Image
import com.globant.equattrocchio.data.response.ResultInput
import com.globant.equattrocchio.domain.models.ResultDomainInput

class ImageMapper {

    fun mapDataModelToDomainModel(inputModel: ResultInput): ResultDomainInput {
        return ResultDomainInput().apply {
            images = inputModel.images.map { mapImage(it) }
        }
    }

    fun mapImage(image: Image): com.globant.equattrocchio.domain.models.Image {
        return com.globant.equattrocchio.domain.models.Image().apply {
            id = image.id
            sourceId = image.sourceId
        }
    }
}

interface BaseMapper<I, O> {
    fun transformTo(input: I): O
    fun transformFrom(output: O): I
}