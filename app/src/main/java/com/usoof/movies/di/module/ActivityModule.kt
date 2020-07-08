package com.usoof.movies.di.module

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.usoof.movies.data.repository.MoviesRepository
import com.usoof.movies.ui.base.BaseActivity
import com.usoof.movies.ui.main.MainViewModel
import com.usoof.movies.ui.main.MovieAdapter
import com.usoof.movies.utils.network.NetworkHelper
import com.usoof.movies.utils.ViewModelProviderFactory
import com.usoof.movies.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ActivityModule(private val activity: BaseActivity<*>) {

    @Provides
    fun provideMainViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        moviesRepository: MoviesRepository
    ): MainViewModel = ViewModelProvider(
        activity, ViewModelProviderFactory(MainViewModel::class) {
            MainViewModel(schedulerProvider, compositeDisposable, networkHelper, moviesRepository)
        }).get(MainViewModel::class.java)

    @Provides
    fun provideMovieAdapter() = MovieAdapter(ArrayList())

    @Provides
    fun provideLinearLayoutManager() = LinearLayoutManager(activity)

}