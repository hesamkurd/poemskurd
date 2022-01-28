package ir.mamhesam.poemskurd.feature.home.viewmodel

import androidx.lifecycle.MutableLiveData
import ir.mamhesam.poemskurd.base.BaseViewModel
import ir.mamhesam.poemskurd.base.customObserver
import ir.mamhesam.poemskurd.data.ResponseBanners
import ir.mamhesam.poemskurd.feature.home.repo.BannersRepository
import ir.mamhesam.poemskurd.utils.PoemSingleObserver

class HomeViewModel(bannersRepository: BannersRepository) : BaseViewModel() {

    val bannersLiveData = MutableLiveData<List<ResponseBanners>>()

    init {
        progressBarLiveData.value = true
        bannersRepository.getBanner().customObserver()
            .doFinally {
                progressBarLiveData.value = false
            }
            .subscribe(object : PoemSingleObserver<List<ResponseBanners>>(compositeDisposable) {
                override fun onSuccess(t: List<ResponseBanners>) {
                    bannersLiveData.value = t
                }

            })
    }
}