package ir.mamhesam.poemskurd.api

import io.reactivex.Single
import ir.mamhesam.poemskurd.data.ResponseBanners
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {

    @GET("home/banners.php")
    fun getBanners(): Single<List<ResponseBanners>>
}

fun retrofitApi(): ApiService{

    val retrofit = Retrofit.Builder()
        .baseUrl("http://poemskurd.zhiran2021.ir/api/v1/")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit.create(ApiService::class.java)
}