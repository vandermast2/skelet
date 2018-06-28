@file:Suppress("UNCHECKED_CAST")

package com.samapps.skelet.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import android.os.Build
import android.os.IBinder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

/**
 * Contains common methods for UI elements.
 *
 * @author Q-DIS
 */
object UiUtils {

    /**
     * Check compatibility with api level = 26 (O)
     *
     * @return true, if current version >= 26
     */
    val isCompatWithO: Boolean
        get() = isCompatWith(Build.VERSION_CODES.O)

    /**
     * Check compatibility with api level = 23 (M)
     *
     * @return true, if current version >= 23
     */
    val isCompatWithM: Boolean
        get() = isCompatWith(Build.VERSION_CODES.M)

    /**
     * Check compatibility with api level = 24 (N)
     *
     * @return true, if current version >= 24
     */
    val isCompatWithN: Boolean
        get() = isCompatWith(Build.VERSION_CODES.N)

    /**
     * Check compatibility with api level = 21 (Lollipop)
     *
     * @return true, if current version >= 21
     */
    val isCompatWithLollipop: Boolean
        get() = isCompatWith(Build.VERSION_CODES.LOLLIPOP)

    /**
     * Check compatibility with api level = 19 (KitKat)
     *
     * @return true, if current version >= 19
     */
    val isCompatWithKitKat: Boolean
        get() = isCompatWith(Build.VERSION_CODES.KITKAT)

    /**
     * Check compatibility with api level = 17 (JELLY_BEAN_MR1)
     *
     * @return true, if current version >= 17
     */
    val isCompatWithJellyBean: Boolean
        get() = isCompatWith(Build.VERSION_CODES.JELLY_BEAN_MR1)

    /**
     * Sets visibility of views.
     * @param visible boolean `true` if views should be visible
     * @param views View[] views
     */
    fun setVisibility(visible: Boolean, vararg views: View) {
        for (view in views) {
            view.visibility = if (visible) View.VISIBLE else View.GONE
        }
    }

    /**
     * Change visibility of views.
     * @param visible boolean `true` if views should be visible
     * @param views View[] views
     */
    fun changeVisibility(visible: Boolean, vararg views: View) {
        for (view in views) {
            view.visibility = if (visible) View.VISIBLE else View.INVISIBLE
        }
    }

    /**
     * Sets visibility of views.
     * @param visible boolean `true` if views should be visible
     * @param views View[] views
     */
    fun setVisibilityContent(visible: Boolean, vararg views: View) {
        for (view in views) {
            view.visibility = if (visible) View.VISIBLE else View.INVISIBLE
        }
    }

    /**
     * Create view
     *
     * @param context android context
     * @param layoutId used for creating new view
     */
    fun <T : View> inflate(context: Context, layoutId: Int): T {
        return LayoutInflater.from(context).inflate(layoutId, null) as T
    }

    /**
     * Create view
     *
     * @param layoutId used for creating new view
     * @param root created view will be added to root.
     */
    fun <T : View> inflate(root: ViewGroup, layoutId: Int): T {
        return LayoutInflater.from(root.context).inflate(layoutId, root, false) as T
    }

    /**
     * Check compatibility with api level = versionCode
     *
     * @param versionCode Android API level
     * @return true, if current version >= versionCode
     */
    private fun isCompatWith(versionCode: Int): Boolean {
        return Build.VERSION.SDK_INT >= versionCode
    }

    /**
     * Check is current device is tablet or not based on specified value for current screen width
     *
     * @param context app context
     * @return true if device is tablet, false otherwise
     */
//    fun isTablet(context: Context): Boolean {
//        return context.resources.getBoolean(R.bool.isTablet)
//    }

    /**
     * Hide soft keyboard. Do nothing if keyboard is not opened
     *
     * @param decorView View
     */
    fun hideKeyboard(decorView: View): Boolean {
        val inputManager = decorView.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        return inputManager.hideSoftInputFromWindow(decorView.windowToken, 0)
    }

    /**
     * Hide soft keyboard. Do nothing if keyboard is not opened
     *
     * @param context [Context] app context
     * @param editText [EditText] to hide keyboard for
     */
    fun hideKeyboard(context: Context, editText: EditText) {
        hideKeyboard(context, editText.windowToken)
    }

    /**
     * Hide soft keyboard. Do nothing if keyboard is not opened
     *
     * @param context [Context] app context
     * @param windowToken [IBinder] window token to hide keyboard for
     */
    fun hideKeyboard(context: Context, windowToken: IBinder) {
        val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken, 0)
    }

    /**
     * Force open keyboard for given [EditText]
     *
     * @param context [Context] app context
     * @param editText [EditText] to show keyboard for
     */
    fun showKeyBoard(context: Context, editText: EditText) {
        val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.showSoftInput(editText, 0)
    }

    /**
     * Execute AsyncTask in parallel mode.
     */
    fun <P, T : AsyncTask<P, *, *>> execute(task: T, vararg params: P) {
        if (isCompatWith(Build.VERSION_CODES.HONEYCOMB)) {
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, *params)
        } else {
            task.execute(*params)
        }
    }

    /**
     * Open browser selection dialog and pass through URL
     *
     * @param context app context
     * @param url web page address
     */
    fun showUrl(context: Context, url: String) {
        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }

    fun dpToPx(context: Context, dp: Float): Int {
        val scale = context.resources.displayMetrics.density
        return Math.round(dp * scale)
    }

//    fun getListCapacityForFastScroll(context: Context): Int {
//        return if (UiUtils.isTablet(context)) Constants.TABLET_LIST_CAPACITY else Constants.PHONE_LIST_CAPACITY
//    }
}
