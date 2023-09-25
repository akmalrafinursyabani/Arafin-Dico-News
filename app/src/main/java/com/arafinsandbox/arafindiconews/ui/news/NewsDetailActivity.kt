package com.arafinsandbox.arafindiconews.ui.news

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.arafinsandbox.arafindiconews.data.news.News
import com.arafinsandbox.arafindiconews.databinding.ActivityNewsDetailBinding

class NewsDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewsDetailBinding

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getNewsParcelable()
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun getNewsParcelable() {
        val news = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(NEWS_DETAIL, News::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<News>(NEWS_DETAIL)
        }

        if (news != null) {
            val newsTitle = news.title
            val newsDate = news.date
            val newsImage = news.image
            val newsCategory = news.category
            val newsArticle = news.article

            binding.newsDetailTitle.text = newsTitle
            binding.newsDetailDate.text = newsDate
            binding.newsDetailImage.setImageResource(newsImage)
            binding.newsDetailTags.text = newsCategory
            binding.newsDetailOverview.text = newsArticle
        } else {
            Toast.makeText(this@NewsDetailActivity, "News was not found!", Toast.LENGTH_SHORT).show()
            finish()
        }

    }

    companion object {
        const val NEWS_DETAIL = "news_detail"
    }
}