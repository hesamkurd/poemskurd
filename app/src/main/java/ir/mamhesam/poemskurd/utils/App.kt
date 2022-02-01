package ir.mamhesam.poemskurd.utils

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import ir.mamhesam.poemskurd.api.ApiService
import ir.mamhesam.poemskurd.api.retrofitApi
import ir.mamhesam.poemskurd.data.ResponseAllPoems
import ir.mamhesam.poemskurd.data.ResponseBanners
import ir.mamhesam.poemskurd.feature.home.adapter.AllPoemAdapter
import ir.mamhesam.poemskurd.feature.home.adapter.BannersAdapter
import ir.mamhesam.poemskurd.feature.home.repo.AllPoemRepository
import ir.mamhesam.poemskurd.feature.home.repo.AllPoemRepositoryImpl
import ir.mamhesam.poemskurd.feature.home.repo.BannersRepository
import ir.mamhesam.poemskurd.feature.home.repo.BannersRepositoryImpl
import ir.mamhesam.poemskurd.feature.home.source.RemoteAllPoemDataSource
import ir.mamhesam.poemskurd.feature.home.source.RemoteBannersDataSource
import ir.mamhesam.poemskurd.feature.home.viewmodel.AllPoemViewModel
import ir.mamhesam.poemskurd.feature.home.viewmodel.HomeViewModel
import ir.mamhesam.poemskurd.services.ImageLoadingImpl
import ir.mamhesam.poemskurd.services.ImageLoadingService
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import timber.log.Timber

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        Fresco.initialize(this)
        Timber.plant(Timber.DebugTree())

        val myModule = module {
            single<ApiService> { retrofitApi() }

            single<ImageLoadingService> { ImageLoadingImpl() }
            //Banners
            factory<BannersRepository> { BannersRepositoryImpl(RemoteBannersDataSource(get())) }
            viewModel { HomeViewModel(get()) }
            factory { (banners: List<ResponseBanners>)-> BannersAdapter(banners,get())}

            //AllPoem
            factory<AllPoemRepository> { AllPoemRepositoryImpl(RemoteAllPoemDataSource(get())) }
            viewModel { AllPoemViewModel(get()) }
            factory { (allPoem: ArrayList<ResponseAllPoems>)-> AllPoemAdapter(allPoem)}
        }

        startKoin {

            androidContext(this@App)
            modules(myModule)
        }
    }
}