package com.usoof.movies.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.usoof.movies.R
import com.usoof.movies.utils.network.NetworkHelper
import com.usoof.movies.utils.common.Resource
import com.usoof.movies.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.net.ssl.HttpsURLConnection

abstract class BaseViewModel(
    protected val schedulerProvider: SchedulerProvider,
    protected val compositeDisposable: CompositeDisposable,
    protected val networkHelper: NetworkHelper
) : ViewModel() {

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    val messageStringId: MutableLiveData<Resource<Int>> = MutableLiveData()
    val messageString: MutableLiveData<Resource<String>> = MutableLiveData()

    protected fun checkInternetConnectionWithMessage(): Boolean =
        if (networkHelper.isNetworkConnected()) {
            true
        } else {
            messageStringId.postValue(Resource.error(R.string.network_connection_error))
            false
        }

    protected fun handleNetworkError(err: Throwable?) =
        err?.let {
            networkHelper.castToNetworkError(it).run {
                when (status_code) {
                    -1 -> {
                        messageStringId.postValue(Resource.error(R.string.network_default_error))
                    }
                    0 -> {
                        messageStringId.postValue(Resource.error(R.string.server_connection_error))
                    }
                    HttpsURLConnection.HTTP_UNAUTHORIZED -> {
                        messageStringId.postValue(Resource.error(R.string.permission_denied))
                    }
                    HttpsURLConnection.HTTP_INTERNAL_ERROR -> {
                        messageStringId.postValue(Resource.error(R.string.network_internal_error))
                    }
                    HttpsURLConnection.HTTP_UNAVAILABLE -> {
                        messageStringId.postValue(Resource.error(R.string.network_server_not_available))
                    }
                    else -> {
                        messageString.postValue(Resource.error(message))
                    }
                }
            }
        }

    abstract fun onCreate()

}