package com.enigma.countryscanner.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.enigma.countryscanner.AppConstant
import com.enigma.countryscanner.R

fun ImageView.loadImage(codeAlpha2Code: String) {
    Glide.with(context)
        .load(AppConstant.BASE_FLAG_IMAGE_URL.plus(codeAlpha2Code).plus(AppConstant.FLAG_URL))
        .error(R.drawable.white_square)
        .centerCrop().into(this)
}