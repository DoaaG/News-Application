package com.example.newsapplication.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiManeger {   // retrofit convert http to json
    companion object {
        private var retrofit: Retrofit? = null
        private fun getInstance(): Retrofit {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl("https://newsapi.org/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }

        fun getApis(): WebServices {
            return getInstance().create(WebServices::class.java)
        }
    }

}

// documentation
//    var retrofit = Retrofit.Builder()
//        .baseUrl("https://api.github.com/")
//        .build()
//
//    var service: GitHubService = retrofit.create(GitHubService::class.java)
