package com.globant.equattrocchio.data.repository.mappers

import com.globant.equattrocchio.data.models.ImageModelService
import com.globant.equattrocchio.data.models.ResultDataModel
import com.globant.equattrocchio.data.realm.models.ImageModelCache
import com.globant.equattrocchio.data.realm.models.ResultCacheModel
import com.globant.equattrocchio.domain.models.Image
import com.globant.equattrocchio.domain.models.ResultDomainModel
import io.realm.RealmList
import javax.inject.Inject

class ImageCacheMapperImpl @Inject constructor(): ImageCacheMapper {

    override fun mapServiceModelToCacheModel(inputModel: ResultDataModel): ResultCacheModel {
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

    override fun mapCacheModelToDomainModel(inputModel: ResultCacheModel): ResultDomainModel {
        return ResultDomainModel().apply {
            images = inputModel.images.map {
                it -> mapImage(it)
            }
        }
    }

    override fun mapImage(image: ImageModelCache): Image {
        return Image().apply {
            id = image.id
            url = image.url
            largeUrl = image.largeUrl
            sourceId = image.sourceId
        }
    }
}
