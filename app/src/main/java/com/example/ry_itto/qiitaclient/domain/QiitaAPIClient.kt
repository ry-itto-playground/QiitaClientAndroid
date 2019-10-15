package com.example.ry_itto.qiitaclient.domain

import com.example.ry_itto.qiitaclient.domain.service.QiitaAPIService
import com.example.ry_itto.qiitaclient.domain.model.Article
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object QiitaAPIClient {
    private const val BASE_URL = "https://qiita.com"

    private fun restClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun fetchArticles(query: String): Observable<List<Article>> {
        val service = restClient().create(QiitaAPIService::class.java)
        return service.findAllArticles(query, 1, 20)
    }
}