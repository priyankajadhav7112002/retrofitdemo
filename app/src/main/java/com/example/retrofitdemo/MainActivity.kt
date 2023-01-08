package com.example.retrofitdemo

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private lateinit var textView : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retService = RetrofitInstance
            .getRetrofitInstance()
            .create(AlbumService::class.java)
        val responseLiveData:LiveData<Response<Albums>> = liveData {
            val response = retService.getAlbums()
            emit(response)
        }
        responseLiveData.observe(this, Observer {
            val albumList = it.body()?.listIterator()
            if (albumList!=null){

                while (albumList.hasNext()){
                    val albumsItem = albumList.next()
                    val result = " " +"Album Title : ${albumsItem.title}"+"\n"+
                                 " " + "Album id : ${albumsItem.id}"+"\n"+
                                 " " + "User id : ${albumsItem.userId}"+"\n\n\n"
                    textView.append(result)
                }
            }
        })
    }
}