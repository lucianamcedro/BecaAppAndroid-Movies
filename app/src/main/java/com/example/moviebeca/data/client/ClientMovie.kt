<<<<<<< HEAD:app/src/main/java/com/example/moviebeca/data/client/ClientMovie.kt
package com.example.moviebeca.data.client

import com.example.moviebeca.data.client.IClientMovie.Companion.baseUrl
=======
package com.example.moviebeca.client

import com.example.moviebeca.client.IClientMovie.Companion.baseUrl
>>>>>>> a3c7f1d620210c58fe3905c2ac6203452b6fde62:app/src/main/java/com/example/moviebeca/client/ClientMovie.kt
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
