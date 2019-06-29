package com.globant.equattrocchio.data.realm.models

import io.realm.RealmList
import io.realm.RealmObject

open class ResultCacheModel(var images: RealmList<ImageModelCache> = RealmList()) : RealmObject()
