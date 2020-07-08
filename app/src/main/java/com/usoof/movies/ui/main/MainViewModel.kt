package com.usoof.movies.ui.main

import androidx.lifecycle.MutableLiveData
import com.usoof.movies.data.model.SearchMovies
import com.usoof.movies.data.repository.MoviesRepository
import com.usoof.movies.ui.base.BaseViewModel
import com.usoof.movies.utils.network.NetworkHelper
import com.usoof.movies.utils.rx.SchedulerProvider
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Function
import java.util.concurrent.TimeUnit

class MainViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    val moviesRepository: MoviesRepository
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    val searchResult = MutableLiveData<List<SearchMovies>>()
    val resetSearch = MutableLiveData<Boolean>()

    override fun onCreate() {

    }

    fun searchMovies(observable: Observable<String>) {
        compositeDisposable.addAll(
            observable
                .debounce(300, TimeUnit.MILLISECONDS)
                .filter {text ->
                    if (!text.isEmpty())
                        resetSearch.postValue(false)
                    else
                        resetSearch.postValue(true)
                    return@filter !text.isEmpty()
                }
                .distinctUntilChanged()
                .switchMap (object : Function<String, ObservableSource<List<SearchMovies>>> {
                    override fun apply(query: String): ObservableSource<List<SearchMovies>> {
                        return moviesRepository.doMovieSearch(query, 1)
                            .subscribeOn(schedulerProvider.io())
                            .doOnError {
                                handleNetworkError(it)
                            }
                    }
                })
                .observeOn(schedulerProvider.ui())
                .subscribe(
                    {
                        searchResult.postValue(it)
                    }
                )
        )
    }

}