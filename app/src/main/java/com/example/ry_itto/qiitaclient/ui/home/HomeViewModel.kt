package com.example.ry_itto.qiitaclient.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.ry_itto.qiitaclient.domain.QiitaAPIClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeViewModel : ViewModel() {
    // Output
    val articles = QiitaAPIClient.fetchArticles("android")
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnError {
            Log.w("articles", it.toString())
        }
}