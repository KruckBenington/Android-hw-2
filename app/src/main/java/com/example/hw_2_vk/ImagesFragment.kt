package com.example.hw_2_vk

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlin.getValue


class ImagesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var buttonAddSquare: Button
    private lateinit var adapter: MyAdapter

    private lateinit var progressBar: ProgressBar
    private lateinit var errorView: View
    private lateinit var retryButton: Button
    private lateinit var pagingProgressBar: ProgressBar

    private val viewModel: MyViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_images, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        recyclerView = view.findViewById(R.id.recycler)
        progressBar = view.findViewById(R.id.progressBar)
        errorView = view.findViewById(R.id.errorView)
        retryButton = view.findViewById(R.id.retryButton)
        pagingProgressBar = view.findViewById(R.id.pagingProgressBar)


        val columns = when (resources.configuration.orientation) {
            Configuration.ORIENTATION_PORTRAIT -> 2
            Configuration.ORIENTATION_LANDSCAPE -> 3
            else -> 2
        }

        val layoutManager = StaggeredGridLayoutManager(
            columns,
            StaggeredGridLayoutManager.VERTICAL
        )
        recyclerView.layoutManager = layoutManager


        adapter = MyAdapter()
        recyclerView.adapter = adapter




        viewModel.mediaItems.observe(viewLifecycleOwner) { items ->
            adapter.submitItems(items)
            updateUi()
        }


        viewModel.isLoading.observe(viewLifecycleOwner) {
            updateUi()
        }


        viewModel.errorMessage.observe(viewLifecycleOwner) {
            updateUi()
        }


        viewModel.isPagingLoading.observe(viewLifecycleOwner) { isPaging ->
            pagingProgressBar.visibility =
                if (isPaging) View.VISIBLE else View.GONE
        }


        retryButton.setOnClickListener { it: View? ->
            viewModel.loadFirstPage()
        }

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (dy <= 0) return

                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisiblePositions = IntArray(layoutManager.spanCount)
                layoutManager.findFirstVisibleItemPositions(firstVisiblePositions)
                val firstVisibleItem = firstVisiblePositions.minOrNull() ?: 0

                val isNearEnd =
                    visibleItemCount + firstVisibleItem >= totalItemCount - 4


                if (isNearEnd) {
                    viewModel.loadNextPage()
                }
            }
        })


        if (viewModel.mediaItems.value.isNullOrEmpty()) {
            viewModel.loadFirstPage()
        }
    }

    private fun updateUi() {
        val isLoading = viewModel.isLoading.value ?: false
        val hasError = viewModel.errorMessage.value != null
        val hasData = !viewModel.mediaItems.value.isNullOrEmpty()

        progressBar.visibility =
            if (isLoading && !hasData) View.VISIBLE else View.GONE

        recyclerView.visibility =
            if (hasData) View.VISIBLE else View.GONE

        errorView.visibility =
            if (hasError && !hasData) View.VISIBLE else View.GONE
    }
}