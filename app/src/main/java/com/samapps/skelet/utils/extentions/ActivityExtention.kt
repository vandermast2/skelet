package com.samapps.skelet.utils.extentions

import androidx.annotation.AnimRes
import androidx.annotation.IdRes
import androidx.annotation.StyleRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment


fun AppCompatActivity.replaceFragmentSafely(fragment: Fragment,
                                            allowStateLoss: Boolean = false,
                                            addToBackStack: Boolean = false,
                                            @IdRes containerViewId: Int,
                                            @StyleRes style: Int = 0,
                                            @AnimRes enterAnimation: Int = 0,
                                            @AnimRes exitAnimation: Int = 0,
                                            @AnimRes popEnterAnimation: Int = 0,
                                            @AnimRes popExitAnimation: Int = 0) {
    val tag = fragment.javaClass.name
    val ft = supportFragmentManager
            .beginTransaction()
            .setTransitionStyle(style)
            .setCustomAnimations(enterAnimation, exitAnimation, popEnterAnimation, popExitAnimation)
            .replace(containerViewId, fragment, tag)
    if (addToBackStack) ft.addToBackStack(tag)
    if (!supportFragmentManager.isStateSaved) {
        ft.commit()
    } else if (allowStateLoss) {
        ft.commitAllowingStateLoss()
    }
}

/**
 * Method to add the fragment. The [fragment] is added to the container view with id
 * [containerViewId] and a [tag]. The operation is performed by the supportFragmentManager.
 * This method checks if fragment exists.
 * @return the fragment added.
 */
fun <T : Fragment> AppCompatActivity.addFragmentSafelfy(fragment: T,
                                                        allowStateLoss: Boolean = false,
                                                        addToBackStack: Boolean = false,
                                                        @IdRes containerViewId: Int,
                                                        @StyleRes style: Int = 0,
                                                        @AnimRes enterAnimation: Int = 0,
                                                        @AnimRes exitAnimation: Int = 0,
                                                        @AnimRes popEnterAnimation: Int = 0,
                                                        @AnimRes popExitAnimation: Int = 0): T {
    val tag = fragment.javaClass.name
    if (!existsFragmentByTag(tag)) {
        val ft = supportFragmentManager
                .beginTransaction()
                .setTransitionStyle(style)
        ft.setCustomAnimations(enterAnimation, exitAnimation, popEnterAnimation, popExitAnimation)
        ft.add(containerViewId, fragment, tag)
        if (addToBackStack)ft.addToBackStack(tag)
        if (!supportFragmentManager.isStateSaved) {
            ft.commit()
        } else if (allowStateLoss) {
            ft.commitAllowingStateLoss()
        }
        return fragment
    }
    return findFragmentByTag(tag) as T
}

/**
 * Method to check if fragment exists. The operation is performed by the supportFragmentManager.
 */
fun AppCompatActivity.existsFragmentByTag(tag: String): Boolean =
        supportFragmentManager.findFragmentByTag(tag) != null

/**
 * Method to get fragment by tag. The operation is performed by the supportFragmentManager.
 */
fun AppCompatActivity.findFragmentByTag(tag: String): Fragment? =
        supportFragmentManager.findFragmentByTag(tag)

