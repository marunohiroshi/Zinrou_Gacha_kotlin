package com.example.zinrou_gacha

import android.content.res.Resources
import android.graphics.Typeface
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("android:src")
    fun ImageView.setImageResource(resourceId: Int) {
        setImageResource(resourceId)
    }

//    @BindingAdapter("textFont")
//    @JvmStatic fun TextView.setTextFont(textView: TextView) {
//        val customFont = Typeface.createFromAsset(resources.assets, "rcsknmMinAna.ttf")
//        textView.typeface = customFont
//    }
}