package ir.mamhesam.poemskurd.utils

import android.app.Application
import ir.mamhesam.poemskurd.api.ApiService
import ir.mamhesam.poemskurd.api.retrofitApi
import ir.mamhesam.poemskurd.feature.home.repo.BannersRepository
import ir.mamhesam.poemskurd.feature.home.repo.BannersRepositoryImpl
import ir.mamhesam.poemskurd.feature.home.source.RemoteBannersDataSource
import ir.mamhesam.poemskurd.feature.home.viewmodel.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import timber.log.Timber

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        val myModule = module {
            single<ApiService> { retrofitApi() }

            //Banners
            factory<BannersRepository> { BannersRepositoryImpl(RemoteBannersDataSource(get())) }
            viewModel { HomeViewModel(get()) }
        }

        startKoin {

            androidContext(this@App)
            modules(myModule)
        }
    }
}