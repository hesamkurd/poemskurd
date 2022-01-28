package ir.mamhesam.poemskurd.feature.home.repo

import io.reactivex.Single
import ir.mamhesam.poemskurd.data.ResponseBanners

interface BannersRepository {
    fun getBanner(): Single<List<ResponseBanners>>
}