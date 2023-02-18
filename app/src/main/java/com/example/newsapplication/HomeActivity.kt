package com.example.newsapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.newsapplication.databinding.ActivityHomeBinding
import com.example.newsapplication.ui.CategoriesFragment
import com.example.newsapplication.ui.NewsFragment

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val drawerLayout: DrawerLayout = binding.drawerLayout
        showFragment(CategoriesFragment())  // check for textview duplication 'pick category'

        binding.appBarCategories.drawerMenuIcon.setOnClickListener {
            drawerLayout.open()
        }
        binding.categoriesInDrawer.setOnClickListener {
            drawerLayout.closeDrawers()
            showFragment(CategoriesFragment())

        }
        binding.settingsInDrawer.setOnClickListener {
            drawerLayout.closeDrawers()
            showFragment(NewsFragment())
//            showFragment(SettingsFragment())
        }
    }

    private fun showFragment(fragment: Fragment) {
        Log.e("fragment", "${R.id.fragment_container}")
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
            .commit()
    }

}