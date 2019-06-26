package com.globant.equattrocchio.data

import com.globant.equattrocchio.data.response.ImageModelCache
import com.globant.equattrocchio.data.response.ResultDataInput
import com.globant.equattrocchio.domain.models.Image
import com.globant.equattrocchio.domain.models.ResultDomainInput

interface ImageMapper {

    fun mapDataModelToDomainModel(inputModel: ResultDataInput): ResultDomainInput
    fun mapImage(image: ImageModelCache): Image
}
