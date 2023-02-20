package com.example.newsapplication

import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapplication.api.ApiManeger
import com.example.newsapplication.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel : ViewModel() {
    var tabsLiveData = MutableLiveData<List<TabsItem?>>()
    var articlesLiveData = MutableLiveData<List<ArticlesItem?>>()
    var progressBarVisibilityLiveData = MutableLiveData<Boolean>()
    var toastLiveData = MutableLiveData<String>()

    fun getTabs(selectedCategory : Category) {
        progressBarVisibilityLiveData.value = true
        ApiManeger.getApis().getTabs(Constants.Apikey,selectedCategory!!.id)?.enqueue(object :
            Callback<TabsResponse> {
            override fun onResponse(
                call: Call<TabsResponse>,
                response: Response<TabsResponse>
            ) {
                progressBarVisibilityLiveData.value = false
                Log.e("onResponseTabs", "${response.body()}")
                if (response.body()?.code == null)
                    tabsLiveData.value = response.body()?.Tabs!!
            }

            override fun onFailure(call: Call<TabsResponse>, error: Throwable) {
                progressBarVisibilityLiveData.value = false
                Log.e("onFailureTabs", "$error")
//                Toast.makeText(requireActivity(), "Try Again!", Toast.LENGTH_LONG).show()
            }

        })
    }
    fun getArticles(tabId: String) {
        progressBarVisibilityLiveData.value = true
        ApiManeger.getApis().getArticles(Constants.Apikey, tabId)
            .enqueue(object : Callback<ArticlesResponse> {
                override fun onResponse(
                    call: Call<ArticlesResponse>,
                    response: Response<ArticlesResponse>
                ) {
                    Log.e("onResponseArticlesBool", "${response.isSuccessful()}")
                    progressBarVisibilityLiveData.value = false
                    Log.e("onResponseArticles", "${response.body()?.articles}")
                    articlesLiveData.value = response.body()?.articles!!

                }

                override fun onFailure(call: Call<ArticlesResponse>, error: Throwable) {
                    progressBarVisibilityLiveData.value = false
                    Log.e("onFailureArticles", "$error")
                    toastLiveData.value = "Try Again!"
//                    Toast.makeText(requireActivity(), "Try Again!", Toast.LENGTH_LONG).show()
                }

            })

    }
}