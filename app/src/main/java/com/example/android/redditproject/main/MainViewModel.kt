package com.example.android.redditproject.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.redditproject.network.Child
import com.example.android.redditproject.network.RedditApi
import kotlinx.coroutines.launch
import java.lang.Exception

class MainViewModel : ViewModel() {

    //Stores the most recent response from the database (JSON data baby)
    private val _status = MutableLiveData<String>()

    private val _redditFeed = MutableLiveData<List<Child>>()

    val redditFeed: LiveData<List<Child>>
        get() = _redditFeed



    init {
        getRedditFeedProperties()
    }

    private fun getRedditFeedProperties() {
        viewModelScope.launch {
            try {

                val listResult = RedditApi.retrofitService.getProperties()

                _status.value = "Success: ${listResult.data.children.size} feed retrieved"
                Log.d("Zelda", "${_status.value}")

                if(listResult.data.children.isNotEmpty())
                {
                    _redditFeed.value = listResult.data.children
                }

                Log.d("Zelda", "${_redditFeed.value?.get(1)?.data?.selftext}")

            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }
        }
    }
}