package com.redbox.mirumon.main.extensions

import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

fun Fragment.applyLoadingState(
    rv: RecyclerView,
    loader: ProgressBar
) {
    rv.adapter = null
    loader.isVisible = true
}

fun Fragment.applySuccessState(
    loader: ProgressBar
) {
    loader.isVisible = false
}




