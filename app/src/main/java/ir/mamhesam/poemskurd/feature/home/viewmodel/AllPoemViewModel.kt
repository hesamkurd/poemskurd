package ir.mamhesam.poemskurd.feature.home.viewmodel

import androidx.lifecycle.MutableLiveData
import ir.mamhesam.poemskurd.R
import ir.mamhesam.poemskurd.base.BaseViewModel
import ir.mamhesam.poemskurd.base.customObserver
import ir.mamhesam.poemskurd.data.ResponseAllPoems
import ir.mamhesam.poemskurd.feature.home.repo.AllPoemRepository
import ir.mamhesam.poemskurd.utils.PoemSingleObserver

class AllPoemViewModel(val allPoemRepository: AllPoemRepository) : BaseViewModel() {

    val allPoemLiveData = MutableLiveData<List<ResponseAllPoems>>()
    val poemByNameLiveData = MutableLiveData<List<ResponseAllPoems>>()
    val poemByYearLiveData = MutableLiveData<List<ResponseAllPoems>>()



    init {

        getAllPoem()
        sortPoemByName()
        sortPoemByYear()

    }

    fun getAllPoem() {

        allPoemRepository.getAllPoem().customObserver()
            .subscribe(object : PoemSingleObserver<List<ResponseAllPoems>>(compositeDisposable) {
                override fun onSuccess(t: List<ResponseAllPoems>) {
                    allPoemLiveData.value = t
                }

            })
    }

    fun sortPoemByName(){
        allPoemRepository.sortPoemByName().customObserver()
            .subscribe(object : PoemSingleObserver<List<ResponseAllPoems>>(compositeDisposable){
                override fun onSuccess(t: List<ResponseAllPoems>) {
                    poemByNameLiveData.value = t
                }

            })
    }
    fun sortPoemByYear(){
        allPoemRepository.sortPoemByYear().customObserver()
            .subscribe(object : PoemSingleObserver<List<ResponseAllPoems>>(compositeDisposable){
                override fun onSuccess(t: List<ResponseAllPoems>) {
                    poemByYearLiveData.value = t
                }

            })
    }


}