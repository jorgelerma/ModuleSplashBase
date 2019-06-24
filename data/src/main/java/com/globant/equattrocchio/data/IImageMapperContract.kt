package com.globant.equattrocchio.data

import com.globant.equattrocchio.data.response.Image
import com.globant.equattrocchio.data.response.ResultInput
import com.globant.equattrocchio.domain.models.ResultDomainInput

interface IImageMapperContract {

    fun mapDataModelToDomainModel(inputModel: ResultInput): ResultDomainInput
    fun mapImage(image: Image): com.globant.equattrocchio.domain.models.Image
}