package com.example.levelup.bindingAdapter

import android.graphics.BitmapFactory
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import java.io.IOException

@BindingAdapter("imageName")
fun setImageFromAssets(view: ImageView, fileName: String) {

    try {
        val bitmap = BitmapFactory.decodeStream(view.context?.assets?.open(fileName))
        view.setImageBitmap(bitmap)
    } catch (e: IOException) {
        e.printStackTrace()
    }

}

@BindingAdapter("imageFile")
fun setImageFromAssets(view: ImageView, fileName: Int) {
    try {
        view.setImageResource(fileName)
    } catch (e: IOException) {
        e.printStackTrace()
    }

}