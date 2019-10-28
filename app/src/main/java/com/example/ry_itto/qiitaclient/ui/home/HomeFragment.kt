package com.example.ry_itto.qiitaclient.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.ry_itto.qiitaclient.R
import io.reactivex.disposables.CompositeDisposable

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private val disposable = CompositeDisposable()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val listView: ListView = view.findViewById(R.id.listView_home)
        disposable.add(homeViewModel.articles
            .onErrorReturn {
                Log.w("reactivex", it)
                emptyList()
            }
            .subscribe({ articles -> listView.adapter = ArrayAdapter(this.context!!, android.R.layout.simple_list_item_1, articles.map { it.title })},
                { t -> t.printStackTrace() }))
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