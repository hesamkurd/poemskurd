package ir.mamhesam.poemskurd.services

import android.widget.ImageView
import com.facebook.drawee.view.SimpleDraweeView
import ir.mamhesam.poemskurd.view.MyImageView

class ImageLoadingImpl: ImageLoadingService {
    override fun loadImage(image: MyImageView, imageUrl: String?) {
        if (image is SimpleDraweeView){
            image.setImageURI(imageUrl)
        }
    }
}