package com.example.ry_itto.qiitaclient.ui.home

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.ry_itto.qiitaclient.R
import com.example.ry_itto.qiitaclient.domain.QiitaAPIClient
import com.example.ry_itto.qiitaclient.domain.model.Article
import java.lang.Exception
import kotlin.concurrent.thread

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val listView: ListView = root.findViewById(R.id.listView_home)
        listView.adapter = ArrayAdapter(this.context!!, android.R.layout.simple_list_item_1, emptyArray<String>())

        val handler = Handler()

        thread {
            try {
                val response = QiitaAPIClient.fetchArticles("android")
                val articleTitles: List<String> = response.body()?.map { it.title } ?: emptyList()

                handler.post(Runnable {
                    listView.adapter = ArrayAdapter(this.context!!, android.R.layout.simple_list_item_1, articleTitles)
                })
                Log.w("retrofit", "fetchArticles")
            } catch (e: Exception) {
                Log.w("retrofit", "fetchArticles : $e")
            }
        }
        return root
    }
}