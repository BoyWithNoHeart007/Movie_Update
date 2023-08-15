package com.example.movielist.Interface

import com.example.movielist.Modal.MovieModal
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiInterface {

    @GET("popular")
    fun getPopular(
        @Query("page")page: Int,
        @Header("Authorization") auth:String
    ): Call<MovieModal>

    @GET("now_playing")
    fun getNowPlaying(
        @Query("page")page : Int,
        @Header("Authorization") auth:String
    ) :Call<MovieModal>

    @GET("top_rated")
    fun getTopRated(
        @Query("page")page : Int,
        @Header("Authorization") auth:String
    ) :Call<MovieModal>

    @GET("upcoming")
    fun getUpcoming(
        @Query("page")page : Int,
        @Header("Authorization") auth:String
    ) :Call<MovieModal>
}