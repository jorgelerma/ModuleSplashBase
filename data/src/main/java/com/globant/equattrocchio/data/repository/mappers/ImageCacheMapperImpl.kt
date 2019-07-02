package com.globant.equattrocchio.data.repository.mappers

import com.globant.equattrocchio.data.models.ImageModelService
import com.globant.equattrocchio.data.models.ResultDataModel
import com.globant.equattrocchio.data.realm.models.ImageModelCache
import com.globant.equattrocchio.data.realm.models.ResultCacheModel
import com.globant.equattrocchio.domain.models.Image
import com.globant.equattrocchio.domain.models.ResultDomainModel
import io.realm.RealmList
import javax.inject.Inject

class ImageCacheMapperImpl @Inject constructor() : ImageCacheMapper {

    override fun mapDataModelToCacheModel(inputModel: ResultDataModel): ResultCacheModel {
        return ResultCacheModel().apply {
            images = RealmList<ImageModelCache>().apply {
                inputModel.images.map {
                    add(mapImage(it))
                }
            }
        }
    }

    override fun mapImage(image: ImageModelService): ImageModelCache {
        return ImageModelCache().apply {
            id = image.id
            url = image.url
            largeUrl = image.largeUrl
            sourceId = image.sourceId.toString()
        }
    }

    override fun mapImage(image: ImageModelCache): ImageModelService {
        return ImageModelService().apply {
            id = image.id
            url = image.url
            largeUrl = image.largeUrl
            sourceId = image.sourceId
        }
    }

    override fun mapCacheModelToDataModel(inputModel: ResultCacheModel): ResultDataModel {
        return ResultDataModel().apply {
            images = inputModel.images.map { mapImage(it) }
        }
    }

    override fun mapDomainModelToCacheModel(input: ResultDomainModel): ResultCacheModel {
        return ResultCacheModel().apply {
            images = RealmList<ImageModelCache>().apply {
                input.images.map {
                    add(mapImage(it))
                }
            }
        }
    }

    override fun mapImage(image: Image): ImageModelCache {
        return ImageModelCache().apply {
            id = image.id
            url = image.url
            largeUrl = image.largeUrl
            sourceId = image.sourceId.toString()
        }
    }
}
