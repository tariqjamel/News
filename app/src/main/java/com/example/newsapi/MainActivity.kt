package com.example.newsapi

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet.Constraint
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.littlemango.stacklayoutmanager.StackLayoutManager
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.newsapi.R


class MainActivity : AppCompatActivity() {
    lateinit var adapter: NewsAdapter
    private var articles = mutableListOf<Article>()
    var pageNum = 1
    var totalResults = -1
    val TAG = "DOT"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        adapter = NewsAdapter(this@MainActivity, articles)
        var newsList = findViewById<RecyclerView>(R.id.newsList)
        newsList.adapter = adapter
        //    newsList.layoutManager = LinearLayoutManager(this@MainActivity)

        val layoutManager = StackLayoutManager(StackLayoutManager.ScrollOrientation.BOTTOM_TO_TOP)
        layoutManager.setPagerMode(true)
        layoutManager.setPagerFlingVelocity(3000)
        layoutManager.setItemChangedListener(object : StackLayoutManager.ItemChangedListener {
            override fun onItemChanged(position: Int) {
                var cardNews = findViewById<ConstraintLayout>(R.id.main)
                cardNews.setBackgroundColor(Color.parseColor(ColorPicker.getColor()))
                Log.d(TAG, "First Visible Item - ${layoutManager.getFirstVisibleItemPosition()}")
                Log.d(TAG, "First Visible Item - ${layoutManager.itemCount}")
                if (totalResults > layoutManager.itemCount && layoutManager.getFirstVisibleItemPosition() >= layoutManager.itemCount - 5) {
                    pageNum++
                    //        getNews()
                }
            }

        })

        val countrySpinner = findViewById<Spinner>(R.id.countrySpinner)
        val countriesArray = resources.getStringArray(R.array.countries)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, countriesArray)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        countrySpinner.adapter = adapter

        countrySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedCountry = parent?.getItemAtPosition(position).toString()
                articles.clear()
                adapter.notifyDataSetChanged()
                getNews(
                    when (selectedCountry) {
                        "Argentina" -> "ar"
                        "Australia" -> "au"
                        "Austria" -> "at"
                        "Belgium" -> "be"
                        "Brazil" -> "br"
                        "Bulgaria" -> "bg"
                        "Canada" -> "ca"
                        "China" -> "cn"
                        "Colombia" -> "co"
                        "Cuba" -> "cu"
                        "Czech Republic" -> "cz"
                        "Egypt" -> "eg"
                        "France" -> "fr"
                        "Germany" -> "de"
                        "Greece" -> "gr"
                        "Hong Kong" -> "hk"
                        "Hungary" -> "hu"
                        "India" -> "in"
                        "Indonesia" -> "id"
                        "Ireland" -> "ie"
                        "Italy" -> "it"
                        "Japan" -> "jp"
                        "Latvia" -> "lv"
                        "Lithuania" -> "lt"
                        "Malaysia" -> "my"
                        "Mexico" -> "mx"
                        "Morocco" -> "ma"
                        "Netherlands" -> "nl"
                        "New Zealand" -> "nz"
                        "Nigeria" -> "ng"
                        "Norway" -> "no"
                        "Philippines" -> "ph"
                        "Poland" -> "pl"
                        "Portugal" -> "pt"
                        "Romania" -> "ro"
                        "Russia" -> "ru"
                        "Saudi Arabia" -> "sa"
                        "Serbia" -> "rs"
                        "Singapore" -> "sg"
                        "Slovakia" -> "sk"
                        "Slovenia" -> "si"
                        "South Africa" -> "za"
                        "South Korea" -> "kr"
                        "Sweden" -> "se"
                        "Switzerland" -> "ch"
                        "Taiwan" -> "tw"
                        "Thailand" -> "th"
                        "Turkey" -> "tr"
                        "UAE" -> "ae"
                        "Ukraine" -> "ua"
                        "United Kingdom" -> "gb"
                        "United States" -> "us"
                        "Venezuela" -> "ve"
                        else -> ""
                    }
                )
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                articles.clear()
                adapter.notifyDataSetChanged()
                getNews("")
            }

        }
        adapter.notifyDataSetChanged()

        newsList.layoutManager = layoutManager
            //   getNews()
        }


    private fun getNews(country: String) {
        Log.d(TAG,"Request send for $pageNum")
//        val news: Call<News> = NewsService.newsInstance.getHeadlines(country = "in", pageNum)
        val news: Call<News> = NewsService.newsInstance.getHeadlines(country, pageNum)
        news.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news: News? = response.body()
                if (news != null) {
                    Log.d("DOT", news.toString())

                    totalResults = news.totalResults
                    articles.addAll(news.articles)
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.e("DOT", "Error in fetching")
            }
        })
    }
}