package com.example.retrofitdemo

import retrofit2.Response
import retrofit2.http.GET

// We are using this website which contains many functions of http requests from here we are fetching data
// https://jsonplaceholder.typicode.com/albums
interface AlbumService {

    @GET("/albums")
    suspend fun getAlbums() : Response<Albums>
}