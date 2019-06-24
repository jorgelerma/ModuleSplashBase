package com.globant.equattrocchio.data

import com.globant.equattrocchio.data.response.Image
import com.globant.equattrocchio.data.response.ResultInput
import com.globant.equattrocchio.domain.models.ResultDomainInput
import javax.inject.Inject

class ImageMapper @Inject constructor() : IImageMapperContract {

    override fun mapDataModelToDomainModel(inputModel: ResultInput): ResultDomainInput {
        return ResultDomainInput().apply {
            images = inputModel.images.map { mapImage(it) }
        }
    }

    override fun mapImage(image: Image): com.globant.equattrocchio.domain.models.Image {
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