package com.samapps.skelet.utils.extentions

import android.view.View


fun View.onClick(body: () -> Unit) {
    setOnClickListener { body() }
}