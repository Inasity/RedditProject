package com.example.android.redditproject.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Listing (
    val kind: String,
    val data: Data
        ): Parcelable

@Parcelize
data class Data(
    val after: String,
    val dist: Int,
    val modhash: String,
    val children: List<Child>
): Parcelable

@Parcelize
data class Child(
    val kind: String,
    val data: RedditFeed
): Parcelable

@Parcelize
data class RedditFeed(
    val id: String,
    val author: String,
    val title: String,
    val num_comments: Int,
    val selftext: String,
    val url: String
): Parcelable
