package com.solo.common.network

import com.solo.common.network.responseModels.Genres
import com.solo.common.network.responseModels.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface ApiNetworkService {
    // Nothing is sent here
    @GET("genre/movie/list")
    suspend fun getAllAvailableCategories(): Genres

    // with_genres - optional, page - optional
    @GET("discover/movie")
    suspend fun searchByGenre(
        @Query("with_genres") categoryId: Int
    ): MoviesResponse

    // query, page - optional
    @GET("search/movie")
    suspend fun searchByName()

    // movie_id
    @GET("movies/getdetails")
    suspend fun getMovieDetails()
}