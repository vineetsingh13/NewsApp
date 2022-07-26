package com.example.news

import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.example.newsfresh.MySingleton
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.news.*
import java.net.URL


class MainActivity : AppCompatActivity(), NewsClicked {

    private lateinit var madapter: NewsAdapter
    private lateinit var url:String
    private var selected: Boolean=true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager=LinearLayoutManager(this)



        url="https://saurav.tech/NewsAPI/top-headlines/category/general/in.json"
        fetchdata()
        general.isSelected=true
        general.setOnClickListener(){
            button_state(general)
            url="https://saurav.tech/NewsAPI/top-headlines/category/general/in.json"
            fetchdata()
        }
        business.setOnClickListener(){
            button_state(business)
            url="https://saurav.tech/NewsAPI/top-headlines/category/business/in.json"
            fetchdata()
        }
        entertainment.setOnClickListener(){
            button_state(entertainment)
            url="https://saurav.tech/NewsAPI/top-headlines/category/entertainment/in.json"
            fetchdata()
        }
        sports.setOnClickListener(){
            button_state(sports)
            url="https://saurav.tech/NewsAPI/top-headlines/category/sports/in.json"
            fetchdata()
        }
        science.setOnClickListener(){
            button_state(science)
            url="https://saurav.tech/NewsAPI/top-headlines/category/science/in.json"
            fetchdata()
        }
        technology.setOnClickListener(){
            button_state(technology)
            url="https://saurav.tech/NewsAPI/top-headlines/category/technology/in.json"
            fetchdata()
        }
        health.setOnClickListener(){
            button_state(health)
            url="https://saurav.tech/NewsAPI/top-headlines/category/health/in.json"
            fetchdata()
        }

        madapter= NewsAdapter(this)
        recyclerView.adapter=madapter
    }

    private fun fetchdata(){

        val jsonObjRequest=JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            {
                val newsJsonArray=it.getJSONArray("articles")
                val newsArray=ArrayList<newsData>()
                for (i in 0 until newsJsonArray.length()){
                    val newsJsonObject=newsJsonArray.getJSONObject(i)
                    val news=newsData(
                        newsJsonObject.getString("title"),
                        //newsJsonObject.getString("author"),
                        newsJsonObject.getString("url"),
                        newsJsonObject.getString("urlToImage"),
                        newsJsonObject.getString("publishedAt"),
                    )
//                    if (news.author=="null"){
//                        news.author="anonymous"
//                    }
                    var newss=news.publishedAt.subSequence(0,10)
                    news.publishedAt=newss.toString()
                    newsArray.add(news)
                }

                madapter.update(newsArray)
            },
            {

            }
        )
        MySingleton.getInstance(this).addToRequestQueue(jsonObjRequest)
    }

    override fun onItemClicked(item: newsData) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(item.url))
    }

    fun button_state(Buttonview: Button){
        if (selected == true){
            if (general.isSelected==true){
                general.isSelected=false
            }
            else if (technology.isSelected==true){
                technology.isSelected=false
            }
            else if (health.isSelected==true){
                health.isSelected=false
            }
            else if (sports.isSelected==true){
                sports.isSelected=false
            }
            else if (science.isSelected==true){
                science.isSelected=false
            }
            else if (entertainment.isSelected==true){
                entertainment.isSelected=false
            }
            else if (business.isSelected==true){
                business.isSelected=false
            }

            Buttonview.isSelected=true
        }
        else if (selected==false){
            selected=true
            Buttonview.isSelected=!Buttonview.isSelected
        }
    }

}