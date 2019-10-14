package com.example.ry_itto.qiitaclient.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ry_itto.qiitaclient.domain.QiitaAPIClient
import com.example.ry_itto.qiitaclient.domain.model.Article
import java.lang.Exception

class HomeViewModel : ViewModel() {
    private val _articles = MutableLiveData<List<Article>>().apply {
        value = emptyList()
        try {
            val response = QiitaAPIClient.fetchArticles("android")
            value = response.body() ?: emptyList()

            Log.w("retrofit", "fetchArticles")
        } catch (e: Exception) {
            value = emptyList()
            Log.w("retrofit", "fetchArticles : $e")
        }
    }
    val articles: LiveData<List<Article>> = _articles
}