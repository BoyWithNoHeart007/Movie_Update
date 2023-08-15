package com.example.movielist.Client

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    companion object {

        val BASE_URL = "https://api.themoviedb.org/3/movie/"
        val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"
        var retrofit: Retrofit? = null

        val Token =
            "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0YWMyZjU2ZGI0MmU4Mjc0YTlhYWI2YWNiMzhiMjlhNCIsInN1YiI6IjY0YWMzNzAzOGEwZTliMDBjNmNjMDBiNCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.LNsenDHEnBRAytOnPdSntXQewq0KmNaq5p1dGnkk0dc"

        fun getApiClient(): Retrofit {

            retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).client(
                    OkHttpClient.Builder().addInterceptor { chain ->

                        val request =
                            chain.request().newBuilder()
                                .addHeader("Authorisation", "Bearer ${Token}")
                                .build()

                        chain.proceed(request)

                    }.build()
                )
                .build()
            return retrofit!!
        }
    }
}