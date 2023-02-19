package com.example.newsapplication.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.newsapplication.Constants
import com.example.newsapplication.R
import com.example.newsapplication.adapters.ArticlesAdapter
import com.example.newsapplication.api.ApiManeger
import com.example.newsapplication.databinding.FragmentNewsBinding
import com.example.newsapplication.model.ArticlesResponse
import com.example.newsapplication.model.Category
import com.example.newsapplication.model.TabsItem
import com.example.newsapplication.model.TabsResponse
import com.google.android.material.tabs.TabLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsFragment(var category: Category) : Fragment() {
    lateinit var newsBinding: FragmentNewsBinding
    lateinit var Adapter: ArticlesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        newsBinding = FragmentNewsBinding.inflate(inflater,container,false)
        return newsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Adapter = ArticlesAdapter(listOf())  // empty list then change the content using notify
        initListeners()
        getTabs()
        newsBinding.itemsRecycler.adapter = Adapter
    }

    fun getTabs() {
        ApiManeger.getApis().getTabs(Constants.Apikey,category.id)?.enqueue(object : Callback<TabsResponse> {
            override fun onResponse(
                call: Call<TabsResponse>,
                response: Response<TabsResponse>
            ) {
                newsBinding.ProgressBar.isVisible = false
                Log.e("onResponseTabs", "${response.body()}")
                if (response.body()?.code == null)
                    response.body()?.Tabs?.let { showTabs(it) }
            }

            override fun onFailure(call: Call<TabsResponse>, error: Throwable) {
                newsBinding.ProgressBar.isVisible = false
                Log.e("onFailureTabs", "$error")
                Toast.makeText(requireActivity(), "Try Again!", Toast.LENGTH_LONG).show()
            }

        })
    }

    fun initListeners() {
        newsBinding.tablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
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
        newsBinding.ProgressBar.isVisible = true
        ApiManeger.getApis().getArticles(Constants.Apikey, tabId)
            .enqueue(object : Callback<ArticlesResponse> {
                override fun onResponse(
                    call: Call<ArticlesResponse>,
                    response: Response<ArticlesResponse>
                ) {
                    Log.e("onResponseArticlesBool", "${response.isSuccessful()}")
//                    if (response.isSuccessful()) {
                    newsBinding.ProgressBar.isVisible = false
                    Log.e("onResponseArticles", "${response.body()?.articles}")
                    response.body()?.articles?.let { Adapter.changeData(it) }
//                    }
                }

                override fun onFailure(call: Call<ArticlesResponse>, error: Throwable) {
                    newsBinding.ProgressBar.isVisible = false
                    Log.e("onFailureArticles", "$error")
                    Toast.makeText(requireActivity(), "Try Again!", Toast.LENGTH_LONG).show()
                }

            })

    }

    private fun showTabs(tabs: List<TabsItem?>) {
        tabs.forEach {
            val newTab = newsBinding.tablayout.newTab()
            newTab.text = it?.name
            newTab.tag = it?.id ?: ""
            newsBinding.tablayout.addTab(newTab)

        }
    }
}