package ir.mamhesam.poemskurd.feature.home.source

import io.reactivex.Single
import ir.mamhesam.poemskurd.api.ApiService
import ir.mamhesam.poemskurd.data.ResponseAllPoems

class RemoteAllPoemDataSource(private val apiService: ApiService): AllPoemDataSource {
    override fun getAllPoem(): Single<List<ResponseAllPoems>> = apiService.getAllPoems()
    override fun sortPoemByName(): Single<List<ResponseAllPoems>> = apiService.sortPoemByName()
    override fun sortPoemByYear(): Single<List<ResponseAllPoems>> = apiService.sortPoemByYear()
}