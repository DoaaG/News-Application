package com.example.newsapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.newsapplication.NewsViewModel
import com.example.newsapplication.adapters.ArticlesAdapter
import com.example.newsapplication.databinding.FragmentNewsBinding
import com.example.newsapplication.model.Category
import com.example.newsapplication.model.TabsItem
import com.google.android.material.tabs.TabLayout

class NewsFragment : Fragment() {
    lateinit var newsBinding: FragmentNewsBinding
    lateinit var Adapter: ArticlesAdapter
    lateinit var viewModel: NewsViewModel
    var selectedCategory: Category? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        newsBinding = FragmentNewsBinding.inflate(inflater, container, false)
        return newsBinding.root
//        viewModel = ViewModelProvider(this)[NewsViewModel::class.java]
    }

    companion object { // another way to select the required category
        fun getInstance(category: Category): NewsFragment {
            var newsFragment = NewsFragment()
            newsFragment.selectedCategory = category
            return newsFragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Adapter = ArticlesAdapter(listOf())  // empty list then change the content using notify
        initListeners()
        viewModel = ViewModelProvider(this)[NewsViewModel::class.java]
        selectedCategory?.let { viewModel.getTabs(it) }
        newsBinding.itemsRecycler.adapter = Adapter
        observeViewModel()

    }

    fun observeViewModel() { // help the news fragment know the change from news view model
        viewModel.tabsLiveData.observe(viewLifecycleOwner, object : Observer<List<TabsItem?>> {
            override fun onChanged(tabs: List<TabsItem?>?) {
                if (tabs != null) {
//                    newsBinding.ProgressBar.isVisible = false    xxx
                    showTabs(tabs)
                }
            }
        })
        viewModel.articlesLiveData.observe(viewLifecycleOwner) { articles ->
            if (articles != null) {
                //newsBinding.ProgressBar.isVisible = false  xxx
                Adapter.changeData(articles)
            }
        }
        viewModel.progressBarVisibilityLiveData.observe(viewLifecycleOwner) {
            if (it) {
                newsBinding.ProgressBar.visibility = View.VISIBLE
            } else
                newsBinding.ProgressBar.visibility = View.GONE

        }
        viewModel.toastLiveData.observe(viewLifecycleOwner){
            Toast.makeText(requireActivity(), it, Toast.LENGTH_LONG).show()
        }
    }


    fun initListeners() {
        newsBinding.tablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val id = tab!!.tag as String
                viewModel.getArticles(id)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                val id = tab!!.tag as String
                viewModel.getArticles(id)
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