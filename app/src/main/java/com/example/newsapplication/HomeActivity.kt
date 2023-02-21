package com.example.newsapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.newsapplication.adapters.OnCategoryClick
import com.example.newsapplication.databinding.ActivityHomeBinding
import com.example.newsapplication.model.Category
import com.example.newsapplication.ui.CategoriesFragment
import com.example.newsapplication.ui.NewsFragment
import com.example.newsapplication.ui.SettingsFragment

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    val categoriesFragment = CategoriesFragment()
    val settingsFragment = SettingsFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val drawerLayout: DrawerLayout = binding.drawerLayout
        unVisibleSearch()
        showFragment(categoriesFragment)

        binding.appBarCategories.drawerMenuIcon.setOnClickListener {
            drawerLayout.open()
        }
        binding.categoriesInDrawer.setOnClickListener {
            drawerLayout.closeDrawers()
            binding.appBarCategories.textInAppbar.text = "News App"
            visibleMenu()
            unVisibleSearch()
            showFragment(categoriesFragment)

        }
        binding.settingsInDrawer.setOnClickListener {
            drawerLayout.closeDrawers()
            binding.appBarCategories.textInAppbar.text = "Settings"
            binding.appBarCategories.drawerSearchIcon.isVisible = false
            unVisibleSearch()
            showFragment(settingsFragment)
        }
        binding.appBarCategories.drawerSearchIcon.setOnClickListener {
            unVisibleMenu()
            visibleSearch()
        }
        binding.appBarCategories.closeIcon.setOnClickListener {
            binding.appBarCategories.searchSpaceInAppbar.text = null
            unVisibleSearch()
            visibleMenu()

        }

        categoriesFragment.onCategoryClick = object : OnCategoryClick {
            override fun OnCategoryClick(category: Category) {
                val fragment = NewsFragment.getInstance(category)
                binding.appBarCategories.textInAppbar.text = category.title
                showFragment(fragment)
            }
        }

    }

    private fun showFragment(fragment: Fragment) {
        Log.e("fragment", "${R.id.fragment_container}")
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
            .commit()
    }

    fun visibleSearch() {
        binding.appBarCategories.searchContent.isVisible = true
//        binding.appBarCategories.closeIcon.isVisible = true
//        binding.appBarCategories.searchSpaceInAppbar.isVisible = true
//        binding.appBarCategories.greenSearchIcon.isVisible = true
    }

    fun unVisibleSearch() {
        binding.appBarCategories.searchContent.isVisible = false
//        binding.appBarCategories.closeIcon.isVisible = false
//        binding.appBarCategories.searchSpaceInAppbar.isVisible = false
//        binding.appBarCategories.greenSearchIcon.isVisible = false

    }

    fun visibleMenu() {
        binding.appBarCategories.drawerSearchIcon.isVisible = true
        binding.appBarCategories.drawerMenuIcon.isVisible = true
        binding.appBarCategories.textInAppbar.isVisible = true
    }

    fun unVisibleMenu() {
        binding.appBarCategories.drawerSearchIcon.isVisible = false
        binding.appBarCategories.drawerMenuIcon.isVisible = false
        binding.appBarCategories.textInAppbar.isVisible = false
    }

}