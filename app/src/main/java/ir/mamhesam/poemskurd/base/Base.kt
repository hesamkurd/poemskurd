package ir.mamhesam.poemskurd.base

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

abstract class BaseActivity : AppCompatActivity(), BaseView {
    override fun setProgressBar(progress: Boolean) {
    }
}

abstract class BaseFragment : Fragment(), BaseView {
    override fun setProgressBar(progress: Boolean) {
    }
}

interface BaseView {
    fun setProgressBar(progress: Boolean)
}

abstract class BaseViewModel : ViewModel(){

    val compositeDisposable = CompositeDisposable()
    var progressBarLiveData = MutableLiveData<Boolean>()

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()

    }
}


fun <T> Single<T>.customObserver(): Single<T> {

    return subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}