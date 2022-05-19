package com.example.moviebeca.data.client

import com.example.moviebeca.data.client.IClientMovie.Companion.baseUrl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ClientMovie {
    companion object {
        private lateinit var retrofit: Retrofit
        private fun getRetrofitInstance(): Retrofit {
            if (!::retrofit.isInitialized) {
                retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
        val movieClientService: IClientMovie by lazy {
            getRetrofitInstance().create(IClientMovie::class.java)
        }
    }
}
