package com.redbox.mirumon.main.extensions

import android.app.Activity
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import com.redbox.mirumon.R

fun Activity.applyTextLoadingState(vararg textViews: TextView) {
    val anim = AnimationUtils.loadAnimation(this, R.anim.blink)
    for (t in textViews) {
        t.animation = anim
    }
}

fun Activity.applyTextSuccessState(vararg textViews: TextView) {
    for (t in textViews) {
        t.animation = null
    }
}

fun Activity.applyErrorState() {
    Toast
        .makeText(this, getText(R.string.error_message), Toast.LENGTH_LONG)
        .show()
}