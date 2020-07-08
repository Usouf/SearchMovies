package com.usoof.movies.di.component

import com.usoof.movies.MyApp
import com.usoof.movies.data.remote.NetworkService
import com.usoof.movies.data.repository.MoviesRepository
import com.usoof.movies.di.module.ApplicationModule
import com.usoof.movies.utils.network.NetworkHelper
import com.usoof.movies.utils.rx.SchedulerProvider
import dagger.Component
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(app: MyApp)

    fun getSchedulerProvider(): SchedulerProvider

    fun getCompositeDisposable(): CompositeDisposable

    fun getNetworkHelper(): NetworkHelper

    fun getNetworkService(): NetworkService

    fun getMoviesRepository(): MoviesRepository

}