package com.example.beupdated

import android.annotation.SuppressLint
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.view.Menu
import android.view.MenuItem

class MainActivity : AppCompatActivity(),clicked {

    lateinit var recyclarview:RecyclerView
    lateinit var adapter: NewsAdapter
    lateinit var progress:ProgressBar
    lateinit var swipe:SwipeRefreshLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclarview = findViewById(R.id.recyclerview)
        swipe = findViewById(R.id.swiperefresh)
        swipe.setColorSchemeColors(resources.getColor(R.color.limegreen))
        progress = findViewById(R.id.progress)
        getData("technology")
        swipe.setOnRefreshListener {
            getData("technology")
//            adapter.notifyDataSetChanged()
            if(swipe.isRefreshing){
                swipe.isRefreshing = false
            }
        }
    }

    private fun getData(category:String) {
        progress.visibility = View.VISIBLE
       retroInstance.NewsInterface.getDataNews(category).enqueue(object : Callback<NewsDataModel?> {
           override fun onResponse(call: Call<NewsDataModel?>, response: Response<NewsDataModel?>) {
               progress.visibility = View.GONE
               Toast.makeText(this@MainActivity,"Be Updated",Toast.LENGTH_LONG).show()
               var news = response.body()
               if(news!=null){
                   adapter = NewsAdapter(this@MainActivity,news.articles, this@MainActivity)
                   recyclarview.layoutManager = LinearLayoutManager(this@MainActivity)
                   recyclarview.adapter = adapter
//                   adapter.notifyDataSetChanged()
               }
           }

           override fun onFailure(call: Call<NewsDataModel?>, t: Throwable) {
               Toast.makeText(this@MainActivity,"Error",Toast.LENGTH_SHORT).show()
           }
       })
    }
    override fun itemclicked(item:ArticlesDataModel){
        customtabchrome(item.url)
    }
    fun customtabchrome(url:String){
        var builder = CustomTabsIntent.Builder()
        val toolbarcolor = CustomTabColorSchemeParams.Builder()
        toolbarcolor.setToolbarColor(ContextCompat.getColor(this,R.color.limegreen))
        builder.setDefaultColorSchemeParams(toolbarcolor.build())
        builder.setShareState(CustomTabsIntent.SHARE_STATE_ON)

        val custombuilder = builder.build()
        custombuilder.launchUrl(this, Uri.parse(url))
    }
}