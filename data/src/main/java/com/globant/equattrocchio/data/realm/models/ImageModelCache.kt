package com.globant.equattrocchio.data.realm.models

import io.realm.RealmObject

open class ImageModelCache(var id: Int = 0, var url: String = "", var largeUrl: String = "", var sourceId: String = "") : RealmObject()
