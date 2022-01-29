package ir.mamhesam.poemskurd.feature.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.mamhesam.poemskurd.R
import ir.mamhesam.poemskurd.data.ResponseBanners
import ir.mamhesam.poemskurd.services.ImageLoadingService
import ir.mamhesam.poemskurd.view.MyImageView

class BannersAdapter(val banners: List<ResponseBanners>, val imageLoadingService: ImageLoadingService):
    RecyclerView.Adapter<BannersAdapter.BannersViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannersViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_banners,parent,false)
        return BannersViewHolder(view)
    }

    override fun onBindViewHolder(holder: BannersViewHolder, position: Int) {
        val itemBanners = banners[position]
        imageLoadingService.loadImage(holder.imgBanners, itemBanners.image)
    }

    override fun getItemCount(): Int = banners.size

    class BannersViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val imgBanners = itemView.findViewById<MyImageView>(R.id.img_banners_home)
    }
}