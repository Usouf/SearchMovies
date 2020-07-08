package com.usoof.movies.di.component

import com.usoof.movies.di.ActivityScope
import com.usoof.movies.di.module.ActivityModule
import com.usoof.movies.ui.main.MainActivity
import dagger.Component

@ActivityScope
@Component(modules = [ActivityModule::class], dependencies = [ApplicationComponent::class])
interface ActivityComponent {

    fun inject(activity: MainActivity)

}