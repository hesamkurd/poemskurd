package ir.mamhesam.poemskurd.feature.home.source

import io.reactivex.Single
import ir.mamhesam.poemskurd.data.ResponseBanners

interface BannersDataSource {
    fun getBanner(): Single<List<ResponseBanners>>

}