package com.example.moviebeca.presenter.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moviebeca.databinding.ActivityMovieFavoritesBinding
import com.example.moviebeca.domain.model.Movie
import com.example.moviebeca.presenter.adapters.FavoriteItemAdapter

class FavoriteMoviesActivity  : AppCompatActivity() {
    private val binding by lazy {
        ActivityMovieFavoritesBinding.inflate(layoutInflater)
    }

    private val movieAdapter by lazy {
        FavoriteItemAdapter(onClickListener = { coin ->
            getCoinFavorites(coin)
        })
    }

   // private val coinDao = CoinDaoRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
   //     CoinDaoRepository.setContext(this)

        binding.icHome.setOnClickListener {
            val listIntent = Intent(this, MainActivity::class.java)
            startActivity(listIntent)
        }

    //    listFavoriteActivity()

        binding.favoriteListRecyclerview.adapter = movieAdapter

    }

   // fun listFavoriteActivity() {
     //   val result = coinDao.listFavorite()
    //    setListAdapter(result)
   // }

    private fun setListAdapter(list: List<Movie>) {
        movieAdapter.submitList(list)
    }

    private fun getCoinFavorites(movie: Movie) {
        val favoriteIntent = Intent(this, FavoriteMoviesActivity::class.java)
        favoriteIntent.putExtra("movie", movie)
        startActivity(favoriteIntent)
    }
}