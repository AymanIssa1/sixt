package com.example.sixttask.ui.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.sixttask.R
import com.example.sixttask.ui.BaseActivity
import com.example.sixttask.ui.carsList.CarsListFragment
import com.example.sixttask.ui.carsMap.CarsMapFragment
import com.zplesac.connectionbuddy.models.ConnectivityEvent
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    private val model: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        model.carsUIModel.observe(this, Observer {
            when {
                it.isLoading -> {
                    progress_bar.visibility = View.VISIBLE
                    error_text_view.visibility = View.GONE
                    // to solve blinking issue
//                    cars_tab_layout.visibility = View.GONE
//                    cars_view_pager.visibility = View.GONE
                }
                it.isSuccess -> {
                    setViewPager()

                    progress_bar.visibility = View.GONE
                    error_text_view.visibility = View.GONE
                    cars_tab_layout.visibility = View.VISIBLE
                    cars_view_pager.visibility = View.VISIBLE
                }
                it.error != null -> {
                    progress_bar.visibility = View.GONE
                    error_text_view.visibility = View.VISIBLE
                    cars_tab_layout.visibility = View.GONE
                    cars_view_pager.visibility = View.GONE
                }
            }
        })
    }

    private fun setViewPager() {
        if (cars_view_pager.adapter == null) {
            val fragments = listOf(
                CarsMapFragment.newInstance() to getString(R.string.map),
                CarsListFragment.newInstance() to getString(R.string.cars)
            )

            val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, fragments)

            cars_view_pager.offscreenPageLimit = fragments.size
            cars_view_pager.adapter = viewPagerAdapter
            cars_tab_layout.setupWithViewPager(cars_view_pager)
        }
    }

    override fun onConnectionChange(event: ConnectivityEvent) {
        super.onConnectionChange(event)
        model.getCars()
    }
}
