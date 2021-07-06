package com.example.glowroadtest.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.glowroadtest.adapter.ImageItemAdapter
import com.example.glowroadtest.viewmodels.ImageItemViewModel
import com.example.glowroadtest.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private lateinit var imageItemViewModel: ImageItemViewModel
    private val adapter = ImageItemAdapter()
    val layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageItemViewModel = ViewModelProvider(this).get(ImageItemViewModel::class.java)
        setUpRecyclerView()
        addScrollListener()
        observeLiveImageData()
        addImages()
    }

    private fun observeLiveImageData() {
        imageItemViewModel.liveImageData.observe(viewLifecycleOwner, Observer {
            adapter.updateList(it)
        })
    }

    private fun setUpRecyclerView() {
        binding.imageRecyclerView.layoutManager = layoutManager
        binding.imageRecyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    var mIsLoading = false
    private fun addScrollListener() {
        val mScrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (mIsLoading)
                    return
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val pastVisibleItems = layoutManager.findFirstVisibleItemPosition()
                if (pastVisibleItems + visibleItemCount >= totalItemCount - 20) {
                    mIsLoading = true
                    //Handler().postDelayed({ addElements() }, 1000)
                    addImages()

                }
            }
        }
        binding.imageRecyclerView.addOnScrollListener(mScrollListener)
    }

    private fun addImages() {
        mIsLoading = false
        val temp: Int? = imageItemViewModel.pageChange.value
        if (temp == null) {
            imageItemViewModel.fetchData(1)
            imageItemViewModel.pageChange.postValue(1)
        } else {
            imageItemViewModel.fetchData(temp + 1)
            imageItemViewModel.pageChange.postValue(temp + 1)
        }
        Log.e("PageChange", "${imageItemViewModel.pageChange.value}")
    }
}