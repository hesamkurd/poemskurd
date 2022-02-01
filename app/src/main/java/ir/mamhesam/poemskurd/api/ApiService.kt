package ir.mamhesam.poemskurd.api

import io.reactivex.Single
import ir.mamhesam.poemskurd.data.ResponseAllPoems
import ir.mamhesam.poemskurd.data.ResponseBanners
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("home/banners.php")
    fun getBanners(): Single<List<ResponseBanners>>

    @GET("home/all_poems.php")
    fun getAllPoems(): Single<List<ResponseAllPoems>>

    @GET("home/sort_poem_name.php")
    fun sortPoemByName(): Single<List<ResponseAllPoems>>

    @GET("home/sort_poem_year.php")
    fun sortPoemByYear(): Single<List<ResponseAllPoems>>
}

fun retrofitApi(): ApiService{

    val retrofit = Retrofit.Builder()
        .baseUrl("http://poemskurd.zhiran2021.ir/api/v1/")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit.create(ApiService::class.java)
}