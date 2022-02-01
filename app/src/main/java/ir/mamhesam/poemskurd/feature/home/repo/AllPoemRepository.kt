package ir.mamhesam.poemskurd.feature.home.repo

import io.reactivex.Single
import ir.mamhesam.poemskurd.data.ResponseAllPoems

interface AllPoemRepository {
    fun getAllPoem():Single<List<ResponseAllPoems>>
    fun sortPoemByName():Single<List<ResponseAllPoems>>
    fun sortPoemByYear():Single<List<ResponseAllPoems>>

}