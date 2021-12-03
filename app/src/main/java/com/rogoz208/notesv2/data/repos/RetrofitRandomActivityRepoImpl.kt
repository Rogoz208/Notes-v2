package com.rogoz208.notesv2.data.repos

import com.rogoz208.notesv2.data.retrofit.RandomActivityApi
import com.rogoz208.notesv2.domain.entities.RandomActivityEntity
import com.rogoz208.notesv2.domain.repos.RandomActivityRepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://www.boredapi.com/api/"

class RetrofitRandomActivityRepoImpl : RandomActivityRepo {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private var api: RandomActivityApi = retrofit.create(RandomActivityApi::class.java)

    override fun getRandomActivitySync(): RandomActivityEntity {
        return api.loadRandomActivity().execute().body()
            ?: throw java.lang.IllegalStateException("null result")
    }

    override fun getRandomActivityAsync(
        onSuccess: (RandomActivityEntity) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        api.loadRandomActivity().enqueue(object : Callback<RandomActivityEntity> {
            override fun onResponse(
                call: Call<RandomActivityEntity>,
                response: Response<RandomActivityEntity>
            ) {
                if (response.isSuccessful) {
                    onSuccess(response.body() ?: throw IllegalStateException("null result"))
                } else {
                    onError(Throwable("unknown error"))
                }
            }

            override fun onFailure(call: Call<RandomActivityEntity>, t: Throwable) {
                onError(t)
            }

        })
    }
}