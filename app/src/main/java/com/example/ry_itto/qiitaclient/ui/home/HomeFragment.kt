package com.example.ry_itto.qiitaclient.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ry_itto.qiitaclient.R
import io.reactivex.disposables.CompositeDisposable

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private val disposable = CompositeDisposable()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)

        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)
        val homeViewAdapter = HomeViewAdapter(this.context!!, mutableListOf())
        recyclerView.apply {
            adapter = homeViewAdapter
            layoutManager = LinearLayoutManager(this.context!!, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        }

        disposable.add(homeViewModel.articles
            .onErrorReturn {
                Log.w("reactivex", it)
                emptyList()
            }
            .map { articles -> articles.map { it.title } }
            .subscribe({ articles ->
                homeViewAdapter.itemList = articles
                homeViewAdapter.notifyDataSetChanged()
            }))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}