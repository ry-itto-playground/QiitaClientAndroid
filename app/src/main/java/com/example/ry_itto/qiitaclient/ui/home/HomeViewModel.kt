package com.example.ry_itto.qiitaclient.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ry_itto.qiitaclient.domain.QiitaAPIClient
import com.example.ry_itto.qiitaclient.domain.model.Article
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import java.lang.Exception

class HomeViewModel : ViewModel() {
    // Output
    val articles = QiitaAPIClient.fetchArticles("android")
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}