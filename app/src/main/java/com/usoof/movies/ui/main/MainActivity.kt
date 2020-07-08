package com.usoof.movies.ui.main

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.usoof.movies.R
import com.usoof.movies.di.component.ActivityComponent
import com.usoof.movies.ui.base.BaseActivity
import com.usoof.movies.utils.common.Logger
import com.usoof.movies.utils.rx.RxSearchObservable
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity<MainViewModel>() {

    @Inject
    lateinit var movieAdapter: MovieAdapter

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    override fun provideLayoutId(): Int = R.layout.activity_main

    override fun injectDependencies(activityComponent: ActivityComponent) =
        activityComponent.inject(this)

    override fun setupView(savedInstanceState: Bundle?) {
        searchView.setIconifiedByDefault(false)
        searchView.onActionViewExpanded()
        searchView.queryHint = "Search Movie"


        LinearSnapHelper().attachToRecyclerView(rvSearchResult)
        rvSearchResult.apply {
            layoutManager = linearLayoutManager
            adapter = movieAdapter
        }

        val observable = RxSearchObservable.fromView(searchView)
        viewModel.searchMovies(observable)

    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.searchResult.observe(this, Observer {
            Logger.d("info", it.toString())
            movieAdapter.updateList(it)
            val view = this.currentFocus
            view?.let { v ->
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                imm?.hideSoftInputFromWindow(v.windowToken, 0)
            }
        })

        viewModel.resetSearch.observe(this, Observer {
            if (it) {
                movieAdapter.clearList()
            }
        })
    }


}