package com.example.newsapplication

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.newsapplication.api.ApiManeger
import com.example.newsapplication.databinding.ActivityHomeBinding
import com.example.newsapplication.model.ArticlesResponse
import com.example.newsapplication.model.TabsItem
import com.example.newsapplication.model.TabsResponse
import com.google.android.material.tabs.TabLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {
    lateinit var homeBinding: ActivityHomeBinding
    lateinit var Adapter: ArticlesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(homeBinding.root)
        Adapter = ArticlesAdapter(listOf())  // empty list then change the content using notify
        initListeners()
        getTabs()
        homeBinding.itemsRecycler.adapter = Adapter
    }

    fun getTabs() {
        ApiManeger.getApis().getTabs(Constants.Apikey)?.enqueue(object : Callback<TabsResponse> {
            override fun onResponse(
                call: Call<TabsResponse>,
                response: Response<TabsResponse>
            ) {
                homeBinding.ProgressBar.isVisible = false
                Log.e("onResponseTabs", "${response.body()}")
                if (response.body()?.code == null)
                    response.body()?.Tabs?.let { showTabs(it) }
            }

            override fun onFailure(call: Call<TabsResponse>, error: Throwable) {
                homeBinding.ProgressBar.isVisible = false
                Log.e("onFailureTabs", "$error")
                Toast.makeText(this@HomeActivity, "Try Again!", Toast.LENGTH_LONG).show()
            }

        })
    }

    fun initListeners() {
        homeBinding.tablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val id = tab!!.tag as String
                getArticles(id)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                val id = tab!!.tag as String
                getArticles(id)
            }

        })
    }

    private fun getArticles(tabId: String) {
        homeBinding.ProgressBar.isVisible = true
        ApiManeger.getApis().getArticles(Constants.Apikey, tabId)
            .enqueue(object : Callback<ArticlesResponse> {
                override fun onResponse(
                    call: Call<ArticlesResponse>,
                    response: Response<ArticlesResponse>
                ) {
                    Log.e("onResponseArticlesBool","${response.isSuccessful()}")
//                    if (response.isSuccessful()) {
                        homeBinding.ProgressBar.isVisible = false
                        Log.e("onResponseArticles", "${response.body()?.articles}")
                        response.body()?.articles?.let { Adapter.changeData(it) }
//                    }
                }

                override fun onFailure(call: Call<ArticlesResponse>, error: Throwable) {
                    homeBinding.ProgressBar.isVisible = false
                    Log.e("onFailureArticles", "$error")
                    Toast.makeText(this@HomeActivity, "Try Again!", Toast.LENGTH_LONG).show()
                }

            })

    }

    private fun showTabs(tabs: List<TabsItem?>) {
        tabs.forEach {
            val newTab = homeBinding.tablayout.newTab()
            newTab.text = it?.name
            newTab.tag = it?.id?: ""
            homeBinding.tablayout.addTab(newTab)

        }
    }
}



