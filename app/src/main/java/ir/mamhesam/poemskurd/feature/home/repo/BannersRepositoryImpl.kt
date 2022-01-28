package ir.mamhesam.poemskurd.feature.home.repo

import io.reactivex.Single
import ir.mamhesam.poemskurd.data.ResponseBanners
import ir.mamhesam.poemskurd.feature.home.source.BannersDataSource

class BannersRepositoryImpl(private val remoteBannersDataSource: BannersDataSource): BannersRepository {
    override fun getBanner(): Single<List<ResponseBanners>> = remoteBannersDataSource.getBanner()
}