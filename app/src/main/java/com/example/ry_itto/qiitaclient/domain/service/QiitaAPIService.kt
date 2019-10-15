package com.example.ry_itto.qiitaclient.domain.service

import com.example.ry_itto.qiitaclient.domain.model.Article
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface QiitaAPIService {
    @GET("api/v2/items")
    fun findAllArticles(@Query("query") query: String,
                        @Query("page") page: Int,
                        @Query("perPage") perPage: Int): Observable<List<Article>>
}