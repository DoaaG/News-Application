package com.example.newsapplication

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.newsapplication.api.ApiManeger
import com.example.newsapplication.databinding.ActivityCategoriesBinding
import com.example.newsapplication.model.TabsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoriesActivity : AppCompatActivity() {
    lateinit var categoriesBinding: ActivityCategoriesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        categoriesBinding = ActivityCategoriesBinding.inflate(layoutInflater)
        setContentView(categoriesBinding.root)
//        getTabs()
    }

//    fun getTabs() {
//        ApiManeger.getApis().getTabs(Constants.Apikey)
//            ?.enqueue(object : Callback<List<TabsResponse?>?> {
//
//                override fun onResponse(
//                    call: Call<List<TabsResponse?>?>,
//                    response: Response<List<TabsResponse?>?>
//                ) {
//                    Log.e("onResponse", "${response.body()}")
//                }
//
//                override fun onFailure(call: Call<List<TabsResponse?>?>, error: Throwable) {
//                    Log.e("onFailure", "$error")
//                    Toast.makeText(this@CategoriesActivity, "Try Again!", Toast.LENGTH_LONG).show()
//                }
//
//            })
//    }
}