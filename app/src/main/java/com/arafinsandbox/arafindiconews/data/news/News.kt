package com.arafinsandbox.arafindiconews.data.news

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class News(
    val title: String,
    val image: Int,
    val date: String, // Untuk kasus ini, sementara akan menggunakan String.
    val category: String,
    val article: String,
) : Parcelable
