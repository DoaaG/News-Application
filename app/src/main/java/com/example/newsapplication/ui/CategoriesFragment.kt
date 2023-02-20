package com.example.newsapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newsapplication.R
import com.example.newsapplication.adapters.CategoriesAdapter
import com.example.newsapplication.adapters.OnCategoryClick
import com.example.newsapplication.adapters.OnNewsClick
import com.example.newsapplication.databinding.FragmentCategoriesBinding
import com.example.newsapplication.model.Category

class CategoriesFragment : Fragment() {
    lateinit var categorybinding: FragmentCategoriesBinding
    lateinit var Adapeter: CategoriesAdapter
    var onCategoryClick :OnCategoryClick? = null
    var categoryList : List<Category> = listOf(
        Category("sports","Sports",R.drawable.sports,R.color.red,true),
        Category("entertainment","Entertainment",R.drawable.politics,R.color.dark_blue,false),
        Category("health","Health",R.drawable.health,R.color.pink,true),
        Category("business","Business",R.drawable.bussines,R.color.orange,false),
        Category("technology","Technology",R.drawable.environment,R.color.light_blue,true),
        Category("science","Science",R.drawable.science,R.color.yellow,false)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        categorybinding = FragmentCategoriesBinding.inflate(inflater,container,false)
        return categorybinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Adapeter = CategoriesAdapter(categoryList)
        categorybinding.pickCategoryRecycler.adapter = Adapeter
        Adapeter.onNewsClickListener = object : OnNewsClick {
            override fun newsClick(category: Category, position: Int) {
                onCategoryClick!!.OnCategoryClick(category)
//                binding.appBarCategories.textInAppbar.text
//                childFragmentManager.beginTransaction().replace(R.id.fragment_container, NewsFragment())
//                    .commit()
            }

        }
    }

}