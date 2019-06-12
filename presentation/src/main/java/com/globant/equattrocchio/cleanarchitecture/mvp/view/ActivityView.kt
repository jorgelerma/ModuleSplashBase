package com.globant.equattrocchio.cleanarchitecture.mvp.view

import android.support.v7.app.AppCompatActivity
import java.lang.ref.WeakReference

open class ActivityView(activity: AppCompatActivity) {

    private var activityRef: WeakReference<AppCompatActivity> = WeakReference(activity)

    fun getActivity(): AppCompatActivity? {
        return activityRef.get()
    }
}
