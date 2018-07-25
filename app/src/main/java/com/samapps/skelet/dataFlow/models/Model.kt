package com.samapps.skelet.dataFlow.models

import android.os.Parcelable

interface Model<T>: Parcelable {
    var id: T?
}