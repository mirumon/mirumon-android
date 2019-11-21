package com.redbox.mirumon.main.extensions

import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.redbox.mirumon.R

fun Fragment.applyLoadingState(
    rv: RecyclerView
) {
    rv.adapter = null
}

fun Fragment.applySuccessState(
    loader: ProgressBar
) {
    loader.visibility = View.GONE
}

fun Fragment.applyTextLoadingState(vararg textViews: TextView) {
    Log.e("Loading", "Loading")
    val anim = AnimationUtils.loadAnimation(context, R.anim.blink)
    for (t in textViews) {
        t.animation = anim
    }
}

fun Fragment.applyTextSuccessState(vararg textViews: TextView) {
    Log.e("Loaded", "Loaded")
    for (t in textViews) {
        t.animation = null
    }

}




