package ir.mamhesam.poemskurd.feature.home.source

import io.reactivex.Single
import ir.mamhesam.poemskurd.data.ResponseAllPoems

interface AllPoemDataSource {
    fun getAllPoem(): Single<List<ResponseAllPoems>>
    fun sortPoemByName():Single<List<ResponseAllPoems>>
    fun sortPoemByYear():Single<List<ResponseAllPoems>>

}