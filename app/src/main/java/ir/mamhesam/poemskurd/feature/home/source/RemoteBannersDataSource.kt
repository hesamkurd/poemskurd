package ir.mamhesam.poemskurd.feature.home.source

import io.reactivex.Single
import ir.mamhesam.poemskurd.api.ApiService
import ir.mamhesam.poemskurd.data.ResponseBanners

class RemoteBannersDataSource(private val apiService: ApiService): BannersDataSource {
    override fun getBanner(): Single<List<ResponseBanners>> = apiService.getBanners()
}