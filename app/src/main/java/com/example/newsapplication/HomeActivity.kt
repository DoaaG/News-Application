package com.example.newsapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
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
    val settingsFragment  = SettingsFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val drawerLayout: DrawerLayout = binding.drawerLayout
        showFragment(categoriesFragment)
        binding.appBarCategories.drawerMenuIcon.setOnClickListener {
            drawerLayout.open()
        }
        binding.categoriesInDrawer.setOnClickListener {
            drawerLayout.closeDrawers()
            showFragment(categoriesFragment)

        }
        binding.settingsInDrawer.setOnClickListener {
            drawerLayout.closeDrawers()
            showFragment(settingsFragment)
//            showFragment(newsFragment)
        }
        categoriesFragment.onCategoryClick = object : OnCategoryClick {
            override fun OnCategoryClick(category: Category) {
                val fragment = NewsFragment.getInstance(category)
                showFragment(fragment)
            }

        }

    }

    private fun showFragment(fragment: Fragment) {
        Log.e("fragment", "${R.id.fragment_container}")
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
            .commit()
    }

}