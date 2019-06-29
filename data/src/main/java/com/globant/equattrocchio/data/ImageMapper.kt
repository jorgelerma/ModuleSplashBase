package com.globant.equattrocchio.data

import com.globant.equattrocchio.data.models.ImageModelService
import com.globant.equattrocchio.data.models.ResultDataModel
import com.globant.equattrocchio.domain.models.Image
import com.globant.equattrocchio.domain.models.ResultDomainModel

interface ImageMapper {

    fun mapDataModelToDomainModel(inputModel: ResultDataModel): ResultDomainModel

    fun mapImage(image: ImageModelService): Image
}
