package com.example.movielist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.movielist.Adapter.MoviesAdapter
import com.example.movielist.Fragments.NowPlaying
import com.example.movielist.Fragments.PopularMovie
import com.example.movielist.Fragments.TopRatedMovie
import com.example.movielist.Fragments.UpcomingMovie
import com.example.movielist.databinding.ActivityMainBinding
import me.ibrahimsn.lib.OnItemSelectedListener

class MainActivity : AppCompatActivity() {

    var page = 1
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var adapter = MoviesAdapter()
        loadFragment(NowPlaying())
        binding.bottomBar.onItemSelectedListener = object : OnItemSelectedListener {

            override fun onItemSelect(pos: Int): Boolean {

                when (pos) {
                    0 -> {

                        loadFragment(NowPlaying())
                    }

                    1 -> {
                        loadFragment(PopularMovie())
                    }

                    2 -> {

                        loadFragment(TopRatedMovie())
                    }

                    3 -> {

                        loadFragment(UpcomingMovie())
                    }

                }

                return false

            }


        }


    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.FrameFragment, fragment)
            .commit()
    }


}