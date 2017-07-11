package com.mykotlinapplication.niit_content.utils

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

/**
 * Created by sandeep.singh on 7/3/2017.
 */
object ActivityUtils{
    /**
     * The `fragment` is added to the container view with id `frameId`. The operation is
     * performed by the `fragmentManager`.

     */
    fun addFragmentToActivity(fragmentManager: FragmentManager,
                              fragment: Fragment, frameId: Int) {
        checkNotNull(fragmentManager)
        checkNotNull(fragment)
        val transaction = fragmentManager.beginTransaction()
        transaction.add(frameId, fragment)
        transaction.commit()
    }

}