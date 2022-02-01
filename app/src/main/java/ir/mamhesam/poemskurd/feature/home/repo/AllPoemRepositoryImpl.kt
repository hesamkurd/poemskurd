package ir.mamhesam.poemskurd.feature.home.repo

import io.reactivex.Single
import ir.mamhesam.poemskurd.data.ResponseAllPoems
import ir.mamhesam.poemskurd.feature.home.source.AllPoemDataSource

class AllPoemRepositoryImpl(private val remoteAllPoemDataSource: AllPoemDataSource): AllPoemRepository {
    override fun getAllPoem(): Single<List<ResponseAllPoems>> = remoteAllPoemDataSource.getAllPoem()
    override fun sortPoemByName(): Single<List<ResponseAllPoems>> = remoteAllPoemDataSource.sortPoemByName()

    override fun sortPoemByYear(): Single<List<ResponseAllPoems>> = remoteAllPoemDataSource.sortPoemByYear()
}