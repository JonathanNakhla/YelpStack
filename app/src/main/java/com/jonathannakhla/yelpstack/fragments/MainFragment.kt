package com.jonathannakhla.yelpstack.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.viewpager2.widget.ViewPager2
import com.jonathannakhla.yelpstack.viewmodels.MainViewModel
import com.jonathannakhla.yelpstack.R
import com.jonathannakhla.yelpstack.adapters.StackAdapter
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

        viewModel.getRestaurants()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                {
                recyclerView.adapter = StackAdapter(it)
                },
                {
                    Log.e(TAG, "Problem grabbing list of restaurants", it)
                }
            ).into(bin)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bin.clear()
    }

}