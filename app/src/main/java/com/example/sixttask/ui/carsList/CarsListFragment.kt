package com.example.sixttask.ui.carsList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.sixttask.R
import com.example.sixttask.data.json.Car
import kotlinx.android.synthetic.main.cars_list_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CarsListFragment : Fragment() {

    private val model: CarsListViewModel by viewModel()
    private var cars: List<Car>? = null

    companion object {
        fun newInstance() = CarsListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.cars_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model.carsUIModel.observe(this, Observer {
            when {
                it.isSuccess -> {
                    cars = it.cars
                    setCarsAdapter()
                }
            }
        })

        model.getCars()
    }

    private fun setCarsAdapter() {
        if (cars_list_recycler_view.adapter == null) {
            cars_list_recycler_view.adapter = CarsAdapter(cars!!, onItemClickListener = {

            })
        } else {
            (cars_list_recycler_view.adapter as CarsAdapter).setCars(cars!!)
            (cars_list_recycler_view.adapter as CarsAdapter).notifyDataSetChanged()
        }
    }

}
