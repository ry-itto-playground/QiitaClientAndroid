package com.example.ry_itto.qiitaclient.domain

import com.example.ry_itto.qiitaclient.domain.service.QiitaAPIService
import com.example.ry_itto.qiitaclient.domain.model.Article
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object QiitaAPIClient {
    private const val BASE_URL = "https://qiita.com"

    private fun restClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun fetchArticles(query: String): Response<List<Article>> {
        val gateway = restClient().create(QiitaAPIService::class.java)
        return gateway.findAllArticles(query, 1, 20).execute()
    }
}