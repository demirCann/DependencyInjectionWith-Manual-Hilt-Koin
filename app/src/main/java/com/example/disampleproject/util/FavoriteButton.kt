package com.example.disampleproject.util

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageButton
import com.example.disampleproject.R

class FavoriteButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : AppCompatImageButton(context, attrs, defStyle) {
    private var isFavorite: Boolean = false
        set(value) {
            field = value
            setImageResource(if (value) R.drawable.filled_favorite else R.drawable.empty_favorite)
        }

    init {
        setImageResource(R.drawable.empty_favorite)
        setOnClickListener { isFavorite = !isFavorite }
    }
}