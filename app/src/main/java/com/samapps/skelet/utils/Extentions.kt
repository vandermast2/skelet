package com.samapps.skelet.utils

import android.view.View


fun View.onClick(body: () -> Unit) {
    setOnClickListener { body() }
}