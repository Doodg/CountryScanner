package com.enigma.countryscanner.extension

import android.opengl.Visibility
import android.view.View

fun View.hide() {
    visibility = View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}