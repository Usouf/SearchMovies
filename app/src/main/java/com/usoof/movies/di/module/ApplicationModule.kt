package com.usoof.movies.di.module

import android.content.Context
import com.usoof.movies.BuildConfig
import com.usoof.movies.MyApp
import com.usoof.movies.data.remote.NetworkService
import com.usoof.movies.data.remote.Networking
import com.usoof.movies.utils.network.NetworkHelper
import com.usoof.movies.utils.rx.RxSchedulerProvider
import com.usoof.movies.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: MyApp) {

    @Provides
    @Singleton
    fun provideApplication(): MyApp = application

    @Provides
    @Singleton
    fun provideContext(): Context = application

    @Provides
    fun provideSchedulerProvider(): SchedulerProvider = RxSchedulerProvider()

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Singleton
    @Provides
    fun provideNetworkHelper(): NetworkHelper =
        NetworkHelper(application)

    @Provides
    @Singleton
    fun provideNetworkService(): NetworkService =
        Networking.create(
            BuildConfig.API_KEY,
            BuildConfig.BASE_URL,
            application.cacheDir,
            10 * 1024 * 1024 // 10MB
        )
}