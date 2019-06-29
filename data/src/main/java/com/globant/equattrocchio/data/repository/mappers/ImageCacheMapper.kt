package com.globant.equattrocchio.data.repository.mappers

import com.globant.equattrocchio.data.models.ImageModelService
import com.globant.equattrocchio.data.models.ResultDataModel
import com.globant.equattrocchio.data.realm.models.ImageModelCache
import com.globant.equattrocchio.data.realm.models.ResultCacheModel
import com.globant.equattrocchio.domain.models.Image
import com.globant.equattrocchio.domain.models.ResultDomainModel

interface ImageCacheMapper {

    fun mapServiceModelToCacheModel(inputModel: ResultDataModel): ResultCacheModel

    fun mapImage(image: ImageModelService): ImageModelCache

    fun mapCacheModelToDomainModel(inputModel: ResultCacheModel): ResultDomainModel

    fun mapImage(image: ImageModelCache): Image
}
