package com.arafinsandbox.arafindiconews

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arafinsandbox.arafindiconews.ui.news.NewsDetailActivity.Companion.NEWS_DETAIL
import com.arafinsandbox.arafindiconews.ui.news.ListNewsAdapter
import com.arafinsandbox.arafindiconews.data.news.News
import com.arafinsandbox.arafindiconews.databinding.ActivityMainBinding
import com.arafinsandbox.arafindiconews.ui.news.NewsDetailActivity
import com.arafinsandbox.arafindiconews.ui.profile.ProfileActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerViewNews: RecyclerView
    private val newsList = ArrayList<News>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerViewNews = binding.rvNews
        recyclerViewNews.setHasFixedSize(true)

        newsList.addAll(getNewsListFromResources())
        displayNewsList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item, menu)
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intentToProfile = Intent(this@MainActivity, ProfileActivity::class.java)
        return when (item.itemId) {
            R.id.about_page -> {
                startActivity(intentToProfile)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun getNewsListFromResources(): ArrayList<News> {
        val newsTitle = resources.getStringArray(R.array.news_title)
        val newsImage = resources.obtainTypedArray(R.array.news_image)
        val newsDate = resources.getStringArray(R.array.news_date)
        val newsCategory = resources.getStringArray(R.array.news_category)
        val newsArticle = resources.getStringArray(R.array.news_article)

        val newsList = ArrayList<News>()

        for (i in newsTitle.indices) {
            val news = News(
                title = newsTitle[i],
                image = newsImage.getResourceId(i, -1),
                date = newsDate[i],
                category = newsCategory[i],
                article = newsArticle[i]
            )

            newsList.add(news)
        }

        return newsList
    }

    private fun displayNewsList() {
        recyclerViewNews.layoutManager = LinearLayoutManager(this)
        val listNewsAdapter = ListNewsAdapter(newsList)
        recyclerViewNews.adapter = listNewsAdapter

        val intentToDetailNews = Intent(this@MainActivity, NewsDetailActivity::class.java)

        listNewsAdapter.setOnItemClickCallback(object : ListNewsAdapter.OnItemClickCallback {
            override fun onItemClicked(data: News) {
                val parseNews = News(
                    title = data.title,
                    image = data.image,
                    date = data.date,
                    category = data.category,
                    article = data.article
                )
                intentToDetailNews.putExtra(NEWS_DETAIL, parseNews)
                startActivity(intentToDetailNews)
            }

        })
    }

    private fun showSelectedNews(news: News) {
        Toast.makeText(this@MainActivity, "You click for ${news.title}", Toast.LENGTH_SHORT).show()
    }
}