package com.samapps.skelet.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Build
import timber.log.Timber
import java.io.Closeable
import java.io.IOException

/**
 * Helper for miscellaneous operations
 */
object MiscellaneousUtils {

    var packageName = ""

    private val EXTRA_BASE = "EXTRA_"
    private val ACTION_BASE = "ACTION_"

    fun getExtra(extraName: String = "") = "$packageName $EXTRA_BASE ${extraName.toUpperCase()}"

    fun getAction(actionName: String) = "$packageName $ACTION_BASE ${actionName.toUpperCase()}"

    /**
     * Close any closeable silently
     * @param closeable any closeable
     */
    fun close(closeable: Closeable?) {
        try {
            closeable?.close()
        } catch (e: IOException) {
            Timber.e("Error MISCELLANEOUS: $e")
        }

    }

    fun getDrawable(context: Context, id: Int?): Drawable? {
        if(id == null) {
            return null
        }
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            context.getDrawable(id)
        } else {
            context.resources.getDrawable(id)
        }
    }

    fun preloadImage(context: Context, imageUrl: String){
//        Glide.with(context)
//                .load(imageUrl)
//                .asBitmap()
//                .encoder(BitmapEncoder(Bitmap.CompressFormat.PNG, HIGH_QUALITY))
//                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                .preload()
    }
}