package com.example.newsapplication.api

import com.example.newsapplication.model.ArticlesResponse
import com.example.newsapplication.model.TabsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface WebServices {
    @GET("v2/top-headlines/sources")
    fun getTabs(@Query("apikey") apikey: String?): Call<TabsResponse>

    @GET("v2/everything")
    fun getArticles(
        @Query("apikey") apikey: String?,
        @Query("sources") tab: String?   //  ("sources") if changed cause null on response
    ): Call<ArticlesResponse>

}


//  Documentation
//interface GitHubService {
//    @GET("users/{user}/repos")
//    fun listRepos(@Path("user") user: String?): Call<List<Repo?>?>?
//    call from retrofit
//}