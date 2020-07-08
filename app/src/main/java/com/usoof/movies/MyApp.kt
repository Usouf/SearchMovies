package com.usoof.movies

import android.app.Application
import com.usoof.movies.di.component.ApplicationComponent
import com.usoof.movies.di.component.DaggerApplicationComponent
import com.usoof.movies.di.module.ApplicationModule

class MyApp : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }

}
