package com.jonathannakhla.yelpstack.fragments

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.jonathannakhla.yelpstack.viewmodels.MainViewModel
import com.jonathannakhla.yelpstack.R
import com.jonathannakhla.yelpstack.adapters.StackAdapter
import com.jonathannakhla.yelpstack.data.Resource
import com.jonathannakhla.yelpstack.ui.*
import com.jonathannakhla.yelpstack.utils.into
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.android.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    companion object {
        private const val TAG = "MainFragment"

        fun newInstance() = MainFragment()
    }

    private val viewModel by viewModel<MainViewModel>()

    private lateinit var recyclerView: ViewPager2
    private lateinit var prevButton: Button
    private lateinit var nextButton: Button

    private val bin by lazy {
        CompositeDisposable()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById<ViewPager2>(R.id.view_pager).apply {
            setPageTransformer(StackSliderTransformer())
        }

        prevButton = view.findViewById<Button>(R.id.prev_button).apply {
            setOnClickListener {
                recyclerView.setCurrentItem(recyclerView.currentItem - 1, true)
            }
        }

        nextButton = view.findViewById<Button>(R.id.next_button).apply {
            setOnClickListener {
                recyclerView.setCurrentItem(recyclerView.currentItem + 1, true)
            }
        }

        requestPermission.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    private fun getRestaurants() {
        viewModel.getRestaurants()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                {
                    when (it) {
                        is Resource.Success, is Resource.Loading -> recyclerView.adapter = StackAdapter(it.data ?: emptyList())
                        is Resource.Error -> Log.e(TAG, "Problem grabbing list of restaurants: ${it.message}")
                    }
                },
                {
                    Log.e(TAG, "Problem grabbing list of restaurants", it)
                }
            ).into(bin)
    }

    private val requestPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()) {isGranted ->
        if(isGranted) getRestaurants()
        else Toast.makeText(context, "Please grant location permission to continue", Toast.LENGTH_SHORT).show()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        bin.clear()
    }

}