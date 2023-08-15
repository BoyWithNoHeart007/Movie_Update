package com.example.movielist.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movielist.Adapter.MoviesAdapter
import com.example.movielist.Client.ApiClient
import com.example.movielist.Interface.ApiInterface
import com.example.movielist.Modal.MovieModal
import com.example.movielist.Modal.ResultsItem
import com.example.movielist.databinding.FragmentTopRatedMovieBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TopRatedMovie : Fragment() {

    var page = 1
    var adapter = MoviesAdapter()
    var list = ArrayList<ResultsItem>()
    lateinit var binding: FragmentTopRatedMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTopRatedMovieBinding.inflate(layoutInflater)

        binding.nested.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {

                page++
                callApi(page)
            }
        })

        callApi(page)
        return binding.root
    }

    private fun callApi(page: Int) {

        var api = ApiClient.getApiClient().create(ApiInterface::class.java)
        api.getTopRated(this.page,"bearer ${ApiClient.Token}").enqueue(object : Callback<MovieModal> {
            override fun onResponse(call: Call<MovieModal>, response: Response<MovieModal>) {

                if (response.isSuccessful) {

                    var movieList = response.body()?.results
                    list.addAll(movieList as ArrayList<ResultsItem>)

                    adapter.setmovies(list)
                    binding.RcvMovie.layoutManager = LinearLayoutManager(context)
                    binding.RcvMovie.adapter = adapter

                }

            }

            override fun onFailure(call: Call<MovieModal>, t: Throwable) {

            }

        })
    }

}
