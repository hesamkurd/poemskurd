package ir.mamhesam.poemskurd.services

import android.widget.ImageView
import ir.mamhesam.poemskurd.view.MyImageView

interface ImageLoadingService {
    fun loadImage(image: MyImageView, imageUrl: String?)
}