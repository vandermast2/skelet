package com.samapps.skelet.utils.extentions

import androidx.annotation.AnimRes
import androidx.annotation.IdRes
import androidx.annotation.StyleRes
import androidx.fragment.app.Fragment


fun Fragment.replaceFragmentSafely(fragment: Fragment,
                                   allowStateLoss: Boolean = false,
                                   @IdRes containerViewId: Int,
                                   @AnimRes enterAnimation: Int = 0,
                                   @AnimRes exitAnimation: Int = 0,
                                   @AnimRes popEnterAnimation: Int = 0,
                                   @AnimRes popExitAnimation: Int = 0) {
    val tag = fragment.javaClass.name
    if (isAdded) {
        val ft = activity?.supportFragmentManager?.beginTransaction()
        ft?.setCustomAnimations(enterAnimation, exitAnimation, popEnterAnimation, popExitAnimation)
        ft?.replace(containerViewId, fragment, tag)
        if (!activity?.supportFragmentManager?.isStateSaved!!) {
            ft?.commit()
        } else if (allowStateLoss) {
            ft?.commitAllowingStateLoss()
        }
    }
}

fun Fragment.replaceFragmentSafely(fragment: Fragment,
                                   allowStateLoss: Boolean = false,
                                   addToBackStack: Boolean = false,
                                   @IdRes containerViewId: Int,
                                   @StyleRes style: Int = 0,
                                   @AnimRes enterAnimation: Int = 0,
                                   @AnimRes exitAnimation: Int = 0,
                                   @AnimRes popEnterAnimation: Int = 0,
                                   @AnimRes popExitAnimation: Int = 0) {
    val tag = fragment.javaClass.name
    val ft = activity?.supportFragmentManager?.beginTransaction()?.setTransitionStyle(style)?.setCustomAnimations(enterAnimation, exitAnimation, popEnterAnimation, popExitAnimation)?.replace(containerViewId, fragment, tag)
    if (addToBackStack) ft?.addToBackStack(tag)
    if (!activity?.supportFragmentManager?.isStateSaved!!) {
        ft?.commit()
    } else if (allowStateLoss) {
        ft?.commitAllowingStateLoss()
    }
}

/**
 * Method to add the fragment. The [fragment] is added to the container view with id
 * [containerViewId] and a [tag]. The operation is performed by the childFragmentManager.
 * This method checks if fragment exists and it is added.
 * @return the fragment added.
 */
fun <T : Fragment> Fragment.addFragmentSafely(fragment: T,
                                              allowStateLoss: Boolean = false,
                                              @IdRes containerViewId: Int,
                                              @AnimRes enterAnimation: Int = 0,
                                              @AnimRes exitAnimation: Int = 0,
                                              @AnimRes popEnterAnimation: Int = 0,
                                              @AnimRes popExitAnimation: Int = 0): T {
    val tag = fragment.javaClass.name
    if (isAdded && !existsFragmentByTag(tag)) {
        val ft = activity?.supportFragmentManager?.beginTransaction()
        ft?.setCustomAnimations(enterAnimation, exitAnimation, popEnterAnimation, popExitAnimation)
        ft?.add(containerViewId, fragment, tag)
        if (!activity?.supportFragmentManager?.isStateSaved!!) {
            ft?.commit()
        } else if (allowStateLoss) {
            ft?.commitAllowingStateLoss()
        }
        return fragment
    }
    return findFragmentByTag(tag) as T
}

/**
 * Method to check if fragment exists. The operation is performed by the childFragmentManager.
 */
fun Fragment.existsFragmentByTag(tag: String): Boolean {
    return activity?.supportFragmentManager?.findFragmentByTag(tag) != null
}

/**
 * Method to get fragment by tag. The operation is performed by the childFragmentManager.
 */
fun Fragment.findFragmentByTag(tag: String): Fragment? {
    return activity?.supportFragmentManager?.findFragmentByTag(tag)
}
